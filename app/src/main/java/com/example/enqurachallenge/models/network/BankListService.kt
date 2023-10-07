package com.example.searchbar.models.network

import com.example.searchbar.models.BankModel
import retrofit2.http.GET

interface BankListService {
    @GET("bankdata")
    suspend fun getBankList(): List<BankModel>
}