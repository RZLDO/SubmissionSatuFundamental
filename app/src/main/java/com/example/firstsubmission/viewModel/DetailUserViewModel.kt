package com.example.firstsubmission.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstsubmission.data.Api.ApiConfig
import com.example.firstsubmission.data.Model.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel:ViewModel() {
    private var _userDetail = MutableLiveData<DetailUserResponse>()
    val userDetail : LiveData<DetailUserResponse> = _userDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading
    companion object{
        const val TAG = "DetailUserModel"
    }

    fun getDetailUser(username:String){
        ApiConfig.ApiService().getDetailUser(username).enqueue(object : Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value =false
                if (response.isSuccessful){
                    _userDetail.value = response.body()
                }else{
                    Log.d(TAG, "onFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure ${t.message}")
            }
        })
    }
}