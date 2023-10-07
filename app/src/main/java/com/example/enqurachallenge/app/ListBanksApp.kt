package com.example.enqurachallenge.app

import android.content.Context
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.enqurachallenge.BankEvent
import com.example.enqurachallenge.data.viewmodel.BankListViewModel
import com.example.enqurachallenge.navigate.BankListAppRouter
import com.example.enqurachallenge.navigate.Screen
import com.example.enqurachallenge.screens.BankListScreen
import com.example.enqurachallenge.screens.SelectedBankInfoScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBanksApp(
    viewModel: BankListViewModel,
    onEvent: (BankEvent) -> Unit,
    context: Context
){
    val state by viewModel.state.collectAsState()
    val selectedItem = state.selectedItem

    Crossfade(targetState = BankListAppRouter.currentScreen, label = "") { currentState ->
        when (currentState.value) {

            is Screen.SelectedBankInfoScreen -> {
                Log.d("SelectedBankInfoScreen", "SelectedBankInfoScreen()")
                Log.d("selectedItem", state.selectedItem.toString())

                selectedItem?.let {
                    SelectedBankInfoScreen(context = context, selectedBank = selectedItem)
                }
            }

            is Screen.BankListScreen -> {
                Log.d("TermsAndConditionScreen", "TermsAndConditionScreen()")
                BankListScreen(viewModel, onEvent)
            }
        }
    }
}