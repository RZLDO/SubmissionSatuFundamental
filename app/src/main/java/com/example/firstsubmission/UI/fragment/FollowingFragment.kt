package com.example.firstsubmission.UI.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firstsubmission.R
import com.example.firstsubmission.databinding.FragmentFollowingBinding


class FollowingFragment : Fragment() {
    private lateinit var _binding : FragmentFollowingBinding
    private var position = 0
    private var username = ""
    companion object {
        const val ARG_POSITION = "0"
        const val ARG_USERNAME = ""
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME).toString()
        }
        if (position == 1){
            _binding.testingText.text = "Following ${username}"
        }else{
            _binding.testingText.text = "Followers"
        }
    }

}