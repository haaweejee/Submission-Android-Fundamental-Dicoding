package id.haaweejee.githubuserv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.haaweejee.githubuserv2.adapter.UserListAdapter
import id.haaweejee.githubuserv2.databinding.ActivityMainBinding
import id.haaweejee.githubuserv2.model.User
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterList: UserListAdapter
    private lateinit var viewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        adapterList = UserListAdapter()
        adapterList.setOnItemClick(object : UserListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            UserViewModel::class.java
        )

        binding.apply {
            rvList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvList.adapter = adapterList
            rvList.setHasFixedSize(true)

            searchView.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null && query.isNotEmpty()) {
                        searchUser()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

            viewModel.listUsers.observe(this@MainActivity) {
                if (it != null) {
                    showLoading(false)
                    adapterList.setData(it)
                    binding.rvList.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun searchUser(){
        binding.apply {
            val query = searchView.query.toString()
            if (query.isEmpty()) return
            binding.rvList.visibility = View.GONE
            showLoading(true)
            viewModel.getSearchUser(query)

        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressCircular.visibility = View.VISIBLE
        } else {
            binding.progressCircular.visibility = View.GONE
        }
    }

}