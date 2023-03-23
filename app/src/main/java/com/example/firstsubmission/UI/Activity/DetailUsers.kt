package com.example.firstsubmission.UI.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.firstsubmission.R
import com.example.firstsubmission.UI.adapter.SectionPagerAdapter
import com.example.firstsubmission.data.Model.DetailUserResponse
import com.example.firstsubmission.databinding.ActivityDetailUsersBinding
import com.example.firstsubmission.viewModel.DetailUserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUsers : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUsersBinding
    private val DetailUserViewModel by viewModels<DetailUserViewModel>()
    companion object{
        const val USERNAME =""
        const val TAG = "Testing"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Users"
        val username = intent.getStringExtra(USERNAME)
        getUsersDetail(username.toString())
        val sectionPagerAdapter = username?.let { SectionPagerAdapter(this, it) }
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs,viewPager){tab, position->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    private fun getUsersDetail(username:String){
        DetailUserViewModel.userDetail.observe(this){
            setUsersData(it)
        }
        DetailUserViewModel.isLoading.observe(this){
            isLoading(it)
        }
        DetailUserViewModel.getDetailUser(username)
    }
    private fun setUsersData(userData: DetailUserResponse){
        Log.d(TAG,userData.toString())
        Glide.with(this)
            .load(userData.avatarUrl)
            .placeholder(R.drawable.baseline_account_circle_24)
            .into(binding.ivImageDetail)
        binding.tvNama.text = userData.name
        binding.tvDetailUsername.text = userData.login
        val following = "${userData.following} Following"
        val follower = "${userData.followers} Followers"
        binding.tvFollowers.text = follower
        binding.tvFollowing.text = following
    }
    private fun isLoading(bool:Boolean){
        binding.progressBar.visibility = if (bool) View.VISIBLE else View.GONE
    }
}