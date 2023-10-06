package com.example.enqurachallenge

import com.example.searchbar.models.BankModel

open class BankEvent {
    data class SelectBank(val selectBank: BankModel): BankEvent()
        object Loading : BankEvent()
    data class Success(val banks: List<BankModel>) : BankEvent()
    data class Error(val message: String) : BankEvent()
}