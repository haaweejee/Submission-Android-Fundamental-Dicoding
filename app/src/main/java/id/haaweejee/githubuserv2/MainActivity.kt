package id.haaweejee.githubuserv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.haaweejee.githubuserv2.adapter.UserListAdapter
import id.haaweejee.githubuserv2.databinding.ActivityMainBinding
import id.haaweejee.githubuserv2.model.User
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterList: UserListAdapter
    private var listUser: ArrayList<User> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterList = UserListAdapter()
        readJson()

        binding.apply {
            rvList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvList.adapter = adapterList
            rvList.setHasFixedSize(true)
            adapterList.setData(listUser)
        }
    }

    private fun readJson() {
        val json: String?
        try {
            json = assets.open("githubuser.json").bufferedReader().use { it.readText() }

            val objectUser = JSONObject(json)
            val users = objectUser.getJSONArray("users")

            for (i in 0 until users.length()) {
                with(users.getJSONObject(i)) {
                    listUser.add(
                        User(
                            name = getString("name"),
                            username = getString("username"),
                            avatar = resources.getIdentifier(
                                getString("avatar"),
                                null,
                                packageName
                            ),
                        )
                    )
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

//    private val listUser : ArrayList<User> get(){
//        val dataUsername = resources.getStringArray(R.array.username)
//        val dataName = resources.getStringArray(R.array.name)
//        val dataAvatar = resources.obtainTypedArray(R.array.avatar)
//
//        //TODO : IF you want add more data you can add the data
//
//        val list = ArrayList<User>()
//
//        for(i in dataName.indices){
//            val user = User(
//                name = dataName[i],
//                username = dataUsername[i],
//                avatar = dataAvatar.getResourceId(i, -1)
//            )
//            list.add(user)
//        }
//
//        return list
//    }

    }
}