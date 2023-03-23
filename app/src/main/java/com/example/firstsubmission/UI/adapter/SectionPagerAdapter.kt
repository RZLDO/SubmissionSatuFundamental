package com.example.firstsubmission.UI.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.firstsubmission.UI.fragment.FollowerFragment
import com.example.firstsubmission.UI.fragment.FollowingFragment

class SectionPagerAdapter(activity :AppCompatActivity, private val username :String): FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        val fragment = FollowerFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowerFragment.ARG_POSITION, position+1)
            putString(FollowerFragment.ARG_USERNAME,username)
        }
        return fragment
    }

}