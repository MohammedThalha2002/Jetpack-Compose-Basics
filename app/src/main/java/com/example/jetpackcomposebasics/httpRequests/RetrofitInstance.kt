package com.example.jetpackcomposebasics.httpRequests

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
        var baseUrl = "http://172.24.110.199:8080"

        val api = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api::class.java)
}