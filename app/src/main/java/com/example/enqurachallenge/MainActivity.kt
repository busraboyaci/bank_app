package com.example.enqurachallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import com.example.enqurachallenge.app.ListBanksApp
import com.example.enqurachallenge.components.InternetWarningDialog
import com.example.enqurachallenge.data.viewmodel.BankListViewModel
import com.example.enqurachallenge.ui.theme.EnquraChallengeTheme
import com.example.searchbar.models.network.ConnectivityObserver
import com.example.searchbar.models.network.NetworkConnectivityObserver
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var isInternetAvailable = true // İnternet durumu değişkeni
    private lateinit var connectivityObserver: ConnectivityObserver


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        val bankListViewModel: BankListViewModel by viewModels()

        // NetworkConnectivityObserver'ı oluştur
        val connectivityObserver = NetworkConnectivityObserver(applicationContext)
        val context = this

        // Bağlantı durumu değiştiğinde çalışacak işlem
        lifecycleScope.launch {
            connectivityObserver.observe()
                .collect { status ->
                    if (status == ConnectivityObserver.Status.Available) {
                        // Internet bağlantısı mevcut, Retrofit isteğini yap
                        bankListViewModel.fetchBankList()
                    } else {

                    }
                }
        }

        setContent {
            EnquraChallengeTheme {
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )

                val dialogShown = remember { mutableStateOf(false) }

                if (status == ConnectivityObserver.Status.Unavailable && isInternetAvailable && !dialogShown.value) {
                    // Internet bağlantısı kesildi ve daha önce uyarı verilmemişse
                    isInternetAvailable = false // Uyarı verildiği işareti
                    println("network status - if: $status")
                    dialogShown.value = true

                } else if (status == ConnectivityObserver.Status.Available) {
                    // Internet bağlantısı geri geldi
                    dialogShown.value = false

                    isInternetAvailable = true // İnternet geri geldiğinde işareti sıfırla
                    // BankListScreen'i göster
                    // BankListScreen(bankListViewModel, onEvent = bankListViewModel::onEvent)
                    ListBanksApp(
                        context = context,
                        viewModel = bankListViewModel,
                        onEvent = bankListViewModel::onEvent
                    )
                }else {
                    dialogShown.value = true
                }

                if (dialogShown.value) {
                    InternetWarningDialog {
                        dialogShown.value = false
                    }
                }
            }
        }
    }

}







