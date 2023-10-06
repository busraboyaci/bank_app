package com.example.enqurachallenge.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.enqurachallenge.navigate.BankListAppRouter
import com.example.enqurachallenge.navigate.Screen
import com.example.enqurachallenge.navigate.SystemBackButtonHandler
import com.example.searchbar.models.BankModel

@Composable
fun SelectedBankInfoScreen(

    selectedBank: BankModel,

){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("selectedBank: ${selectedBank.bankAddress}")

    }
    SystemBackButtonHandler {
        BankListAppRouter.navigateTo(Screen.BankListScreen)
    }
}