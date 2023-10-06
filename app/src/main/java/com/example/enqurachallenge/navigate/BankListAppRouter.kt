package com.example.enqurachallenge.navigate

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


open class Screen {
    object BankListScreen : Screen()
    object SelectedBankInfoScreen : Screen()
}


object BankListAppRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.BankListScreen)
    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}