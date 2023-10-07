package com.example.enqurachallenge.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enqurachallenge.R
import com.example.searchbar.models.BankModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CircularProgressIndicatorWithDelay() {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val timerDuration = 5000L // 5 seconds
        launch {
            delay(timerDuration)
            isLoading = false
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun SelectedBankInfoScreenTopRow(bank: BankModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(70.dp)
            .background(colorResource(id = R.color.navy))
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.navy)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${bank.city}",
            fontSize = 25.sp,
            color = colorResource(id = R.color.beige),
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
        )
    }
}

@Composable
fun NavigationFloatingActionButton(
    onClick: () -> Unit,
    buttonText: String
) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = { Icon(Icons.Default.NearMe, contentDescription = null) },
        text = { Text(text = buttonText) },
        modifier = Modifier.sizeIn(minHeight = 0.dp)
    )
}

@Composable
fun InternetWarningDialog(function: () -> Unit) {
    AlertDialog(
        title = {
            Text("İnternet bağlantısı bulunamadı")
        },
        text = {
            Text("İnternet bağlantınızı kontrol edin ve tekrar deneyin.")
        },
        confirmButton = {
            // Empty composable, no button
        },
        onDismissRequest = {
            // Empty composable, dismissing on outside click
        }
    )
}

@Composable
fun NoBankDataDialog() {
    AlertDialog(
        title = {
            Text("Banka verisi yok")
        },
        text = {
            Text("Tekrar deneyin.")
        },
        confirmButton = {
            // Empty composable, no button
        },
        onDismissRequest = {
            // Empty composable, dismissing on outside click
        }
    )
}

@Composable
fun NoSearchDataDialog() {
    AlertDialog(
        title = {
            Text("Aradığınız şehir bulunamadı")
        },
        text = {
            Text("Tekrar deneyin.")
        },
        confirmButton = {
            // Empty composable, no button
        },
        onDismissRequest = {
            // Empty composable, dismissing on outside click
        }
    )
}











