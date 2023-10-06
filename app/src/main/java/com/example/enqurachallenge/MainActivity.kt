package com.example.enqurachallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.example.enqurachallenge.app.ListBanksApp
import com.example.enqurachallenge.data.viewmodel.BankListViewModel
import com.example.enqurachallenge.screens.BankListScreen
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

        // Bağlantı durumu değiştiğinde çalışacak işlem
        lifecycleScope.launch {
            connectivityObserver.observe()
                .collect { status ->
                    if (status == ConnectivityObserver.Status.Available) {
                        // Internet bağlantısı mevcut, Retrofit isteğini yap
                        bankListViewModel.fetchBankList()
                    }
                }
        }

        setContent {
            EnquraChallengeTheme {
                // A surface container using the 'background' color from the theme
                // Internet durumunu izlemek için bir observer

                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )
                println("isInternetAvailable: $isInternetAvailable")
                println("network status: $status")

                // İnternet durumu değiştiğinde çalışacak işlem
                if (status == ConnectivityObserver.Status.Unavailable && isInternetAvailable) {
                    // Internet bağlantısı kesildi ve daha önce uyarı verilmemişse
                    println("isInternetAvailableeeee: $isInternetAvailable")
                    Text(text = "network status: $status")

                    isInternetAvailable = false // Uyarı verildiği işareti

                } else if (status == ConnectivityObserver.Status.Available) {
                    // Internet bağlantısı geri geldi
                    isInternetAvailable = true // İnternet geri geldiğinde işareti sıfırla
                    // BankListScreen'i göster
//                    BankListScreen(bankListViewModel, onEvent = bankListViewModel::onEvent)
                    ListBanksApp(bankListViewModel, onEvent = bankListViewModel::onEvent)

                }

            }
        }
    }
}







