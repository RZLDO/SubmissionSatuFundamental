package com.example.firstsubmission.data.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        private const val BASE_URL = " https://api.github.com"
        private const val HEADERS  = "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"

        fun ApiService():ApiService{
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeader = req.newBuilder()
                    .header("Autorization", HEADERS)
                    .build()
                chain.proceed(requestHeader)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}