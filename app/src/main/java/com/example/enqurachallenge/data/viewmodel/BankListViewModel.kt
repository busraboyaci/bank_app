package com.example.enqurachallenge.data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enqurachallenge.BankEvent
import com.example.enqurachallenge.BankState
import com.example.enqurachallenge.data.uistate.MainUiState
import com.example.searchbar.models.BankModel
import com.example.searchbar.models.network.BankListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BankListViewModel : ViewModel() {

    private val repository = BankListRepository()

    private val _banks = MutableLiveData<List<BankModel>?>(null)
    val bankList: LiveData<List<BankModel>?> = _banks
    private var bankUIState = mutableStateOf(MainUiState())
    private val _shortType = MutableStateFlow(SortType.BANK_NAME)

    private val _bank = _shortType



    //    val event: StateFlow<BankEvent> = _event
    private val bankState = MutableStateFlow(BankState())

    val state = combine(bankState, _shortType, _bank) { bankState, sortType, banks ->
        bankState.copy(
            sortType = sortType,
            banks = banks as? List<BankModel> ?: emptyList() // banks'i BankModel listesine dönüştür veya boş liste oluştur
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BankState())


    fun fetchBankList() {
        viewModelScope.launch {
            try {
                val banks = repository.getBankList()
                _banks.value = banks
//                _state.value = BankState.Success(banks)

                println("bank listeee: $banks")
            } catch (e: Exception) {
                // Handle error
//                _state.value = BankState.Error("An error occurred: ${e.message}")

            }
        }
    }

    fun onEvent(event: BankEvent) {
        when (event) {
            is BankEvent.SelectBank -> {
                viewModelScope.launch {
                    bankState.update {
                        it.copy(
                            selectedItem = event.selectBank
                        )
                    }
                }
            }
        }

    }
}