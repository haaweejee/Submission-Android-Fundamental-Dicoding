package id.haaweejee.githubuserv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import id.haaweejee.githubuserv2.adapter.SectionPagerAdapter
import id.haaweejee.githubuserv2.databinding.ActivityDetailBinding
import id.haaweejee.githubuserv2.model.User

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USER_DATA = "users"
        const val EXTRA_USERNAME = "username"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.txt_followers,
            R.string.txt_following
        )
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        username?.let { viewModel.setDetailUser(it) }
        showTabLayout()

        viewModel.getUserDetail().observe(this){
            binding.tvUsername.text = it.login
            binding.tvName.text = it.name
            Glide.with(this)
                .load(it.avatar_url)
                .centerCrop()
                .into(binding.ivAvatar)
            }
    }

    private fun showTabLayout(){
        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)
        val sectionsPagerAdapter = SectionPagerAdapter(this, bundle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

}

