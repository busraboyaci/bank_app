package com.example.enqurachallenge.data.uievents

import com.example.searchbar.models.BankModel

open class MainUIEvent {
    data class SearchTextChanged(val searchText: String): MainUIEvent()
    data class SelectBank(val selectBank: BankModel): MainUIEvent()

    object BankItemClicked : MainUIEvent()

}