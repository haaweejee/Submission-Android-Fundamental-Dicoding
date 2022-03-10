package id.haaweejee.githubuserv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.haaweejee.githubuserv2.databinding.ActivityDetailBinding
import id.haaweejee.githubuserv2.model.User

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USER_DATA = "users"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val users = intent.getParcelableExtra<User>(EXTRA_USER_DATA) as User
        binding.tvName.text = users.name
        binding.tvUsername.text = users.username
        binding.ivAvatar.setImageResource(users.avatar)

    }

}