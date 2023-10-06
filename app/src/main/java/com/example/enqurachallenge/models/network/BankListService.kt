package com.example.searchbar.models.network

import android.util.Log
import com.example.searchbar.models.BankModel
import com.example.searchbar.models.network.RetrofitInstance.bankListService
import retrofit2.http.GET

interface BankListService {
    @GET("bankdata")
    suspend fun getBankList(): List<BankModel>
}