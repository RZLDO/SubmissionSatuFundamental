package com.example.firstsubmission.viewModel

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstsubmission.data.Api.ApiConfig
import com.example.firstsubmission.data.Model.FollowersResponse
import com.example.firstsubmission.data.Model.FollowersResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel:ViewModel() {
    private val _followerData = MutableLiveData<List<FollowersResponseItem>>()
    val followerData : LiveData<List<FollowersResponseItem>> = _followerData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    companion object{
        const val TAG = "FollowersViewModel"
    }

    fun getFollowers(username:String){
        ApiConfig.ApiService().getFollowers(username).enqueue(object :Callback<List<FollowersResponseItem>>{
            override fun onResponse(
                call: Call<List<FollowersResponseItem>>,
                response: Response<List<FollowersResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _followerData.value = response.body()
                }else{
                    Log.d(TAG, "onFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowersResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG,"onFailure ${t.message}")

            }
        })
    }

    fun getFollowing(username: String){
        ApiConfig.ApiService().getFollowing(username).enqueue(object :Callback<List<FollowersResponseItem>>{
            override fun onResponse(
                call: Call<List<FollowersResponseItem>>,
                response: Response<List<FollowersResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _followerData.value = response.body()
                }else{
                    Log.d(TAG, "onFailure : ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<FollowersResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure : ${t.message}")
            }
        })
    }
}