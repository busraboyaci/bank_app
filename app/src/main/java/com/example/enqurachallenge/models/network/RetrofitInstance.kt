package com.example.searchbar.models.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://raw.githubusercontent.com/fatiha380/mockjson/main/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val bankListService: BankListService by lazy {
        retrofit.create(BankListService::class.java)
    }
}