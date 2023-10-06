package com.example.searchbar.models.network

import android.util.Log
import com.example.searchbar.models.BankModel


class BankListRepository {
    private val bankListService = RetrofitInstance.bankListService

    suspend fun getBankList(): List<BankModel> {
        val response = bankListService.getBankList()
        Log.d("API_RESPONSE", "API Response: $response")
        return response
    }
}