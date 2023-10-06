package com.example.enqurachallenge

import com.example.enqurachallenge.data.viewmodel.SortType
import com.example.searchbar.models.BankModel

//sealed class BankState {
//    object Loading : BankState()
//    data class Success(val banks: List<BankModel>) : BankState()
//    data class Error(val message: String) : BankState()
//}

data class BankState(
    val banks: List<BankModel> = emptyList(),
    val bankCity: String = "",
    val bankDistrict: String = "",
    val bankBranch: String = "",
    val bankType: String = "",
    val bankCode: String = "",
    val addressName: String = "",
    val bankAddress: String = "",
    val postCode: String = "",
    val onOffLine: String = "",
    val onOffSite: String = "",
    val regionalCoordinator: String = "",
    val nearestAtm: String = "",
    val selectedItem: BankModel? = null, // Seçili öğe
    val sortType: SortType = SortType.BANK_NAME,


    )