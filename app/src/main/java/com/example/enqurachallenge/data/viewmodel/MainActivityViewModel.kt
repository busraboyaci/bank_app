package com.example.enqurachallenge.data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.enqurachallenge.data.uievents.MainUIEvent
import com.example.enqurachallenge.data.uistate.MainUiState
import com.example.searchbar.models.network.ConnectivityObserver

class MainActivityViewModel(): ViewModel() {
    var networkConnectProgressBar = mutableStateOf(false)
    var mainUIState = mutableStateOf(MainUiState())

    fun onEvent(event: MainUIEvent) {
        when (event) {
            is MainUIEvent.SearchTextChanged -> {
                mainUIState.value = mainUIState.value.copy(
                    searchText = event.searchText
                )
            }

            is MainUIEvent.BankItemClicked -> {
//                bankItemInfo()
            }
        }

    }
}