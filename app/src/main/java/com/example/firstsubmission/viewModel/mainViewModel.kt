package com.example.firstsubmission.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstsubmission.data.Api.ApiConfig
import com.example.firstsubmission.data.Api.ApiService
import com.example.firstsubmission.data.Model.GithubResponse
import com.example.firstsubmission.data.Model.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {
    private val _userData = MutableLiveData<List<ItemsItem>>()
    val userData : LiveData<List<ItemsItem>> = _userData
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MainActivityModel"
    }

    fun getUserData(username:String){
        ApiConfig.ApiService().searchUser(username).enqueue(object : Callback<GithubResponse>{
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _userData.value = response.body()?.items as List
                }else{
                    Log.e(TAG,"onFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure ${t.message}")
            }
        })
    }
}