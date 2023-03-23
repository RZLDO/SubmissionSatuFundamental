package com.example.firstsubmission.UI.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstsubmission.UI.Activity.DetailUsers
import com.example.firstsubmission.UI.adapter.FollowersAdapter
import com.example.firstsubmission.data.Model.FollowersResponseItem
import com.example.firstsubmission.databinding.FragmentFollowerBinding
import com.example.firstsubmission.viewModel.FollowersViewModel

class FollowerFragment : Fragment() {
    private lateinit var _binding : FragmentFollowerBinding
    private val followersViewModel by viewModels<FollowersViewModel>()
    companion object{
        const val ARG_POSITION = "0"
        const val ARG_USERNAME =""
    }
    private var position = 0
    private var username = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            position = it.getInt(ARG_POSITION)
            username= it.getString(ARG_USERNAME).toString()
        }
        if (position==1){
            getFollowersData(username)
        }else {
            getFollowingData(username)
        }
        setDividers()
    }
    private fun setDividers(){
        val linearLayoutManager = LinearLayoutManager(context)
        _binding.rvFollowers.layoutManager = linearLayoutManager
        val itemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        _binding.rvFollowers.addItemDecoration(itemDecoration)
    }

    private fun getFollowersData(username:String){
        followersViewModel.followerData.observe(viewLifecycleOwner){
            setFollowersData(it)
        }
        followersViewModel.isLoading.observe(viewLifecycleOwner){
            isLoading(it)
        }
        followersViewModel.getFollowers(username)
    }
    private fun getFollowingData(username: String){
        followersViewModel.followerData.observe(viewLifecycleOwner){
            setFollowersData(it)
        }
        followersViewModel.isLoading.observe(viewLifecycleOwner){
            isLoading(it)
        }
        followersViewModel.getFollowing(username)
    }

    private fun setFollowersData(followersData: List<FollowersResponseItem>) {
        _binding.rvFollowers.setHasFixedSize(true)
        _binding.rvFollowers.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = FollowersAdapter(followersData)
        adapter.setOnItemClickListener {
            val intent = Intent(context, DetailUsers::class.java)
            intent.putExtra(DetailUsers.USERNAME, followersData[it].login)
            startActivity(intent)
        }
        _binding.rvFollowers.adapter = adapter
    }
    private fun isLoading(bool:Boolean){
        _binding.progressBar.visibility = if (bool) View.VISIBLE else View.GONE
    }
}