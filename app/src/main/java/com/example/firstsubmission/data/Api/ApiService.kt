package com.example.firstsubmission.data.Api

import com.example.firstsubmission.data.Model.*
import retrofit2.Call
import retrofit2.http.*
interface ApiService {
    @GET("search/users")
    fun searchUser(@Query("q") username:String) : Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String):Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username")username: String):Call<List<FollowersResponseItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username")username: String):Call<List<FollowersResponseItem>>
}