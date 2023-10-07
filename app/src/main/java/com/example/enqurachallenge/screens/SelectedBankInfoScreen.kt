package com.example.enqurachallenge.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material3.Card

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.enqurachallenge.R
import com.example.enqurachallenge.components.NavigationFloatingActionButton
import com.example.enqurachallenge.components.OpenGoogleMaps
import com.example.enqurachallenge.components.SelectedBankInfoScreenTopRow
import com.example.enqurachallenge.navigate.BankListAppRouter
import com.example.enqurachallenge.navigate.Screen
import com.example.enqurachallenge.navigate.SystemBackButtonHandler
import com.example.enqurachallenge.ui.theme.Beige
import com.example.enqurachallenge.ui.theme.Navy
import com.example.searchbar.models.BankModel

@Composable
fun SelectedBankInfoScreen(
    selectedBank: BankModel,
    context: Context
) {

    val mapIntent =
        Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=${selectedBank.bankAddress}"))
    mapIntent.setPackage("com.google.android.apps.maps")
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.beige))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.beige))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SelectedBankInfoScreenTopRow(selectedBank)
            Spacer(modifier = Modifier.width(15.dp))

            Card(
                modifier = Modifier
                    .padding(PaddingValues(20.dp)),
            ) {
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("İlçe: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.district)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )

                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("Şube: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.bankBranch)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("Banka Tipi: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.bankType)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("Banka Codu: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.bankCode)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("Banka Codu: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.bankCode)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("Adres Adı: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.addressName)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("Adres Adı: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.addressName)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("Adres: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.bankAddress)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("Posta Kodu: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.postCode)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("On Offline: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.onOffLine)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("On OffSite: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.onOffSite)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("Bölge Koordinatörlüğü: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.regionalCoordinator)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    buildAnnotatedString {
                        // İlk kısım (Adres:) normal stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal,
                                color = Navy // Normal stil rengi
                            )
                        ) {
                            append("En Yakın Atm: ")
                        }

                        // İkinci kısım (bankAddress) kalın stilde
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Normal, // Kalın stil
                                fontStyle = FontStyle.Normal,
                                color = Navy // Kalın stil rengi
                            )
                        ) {
                            append(selectedBank.nearestAtm)
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(15.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),


                    ) {
                    NavigationFloatingActionButton(
                        onClick = {
                            openGoogleMaps(context = context, selectedBank.bankAddress)
                        },

                        buttonText = "Yol Tarifi al"
                    )
                }
            }

        }

    }
    SystemBackButtonHandler {
        BankListAppRouter.navigateTo(Screen.BankListScreen)
    }

}

fun openGoogleMaps(context: Context, address: String?) {
//    val intentUri = Uri.parse("google.navigation:q=$address")
    println("adress: $address")
    val encodedAddress = Uri.encode(address)

    // Construct the intent URI for navigation
    val intentUri = Uri.parse("google.navigation:q=$encodedAddress")

    println("Address: $address")
    val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
    mapIntent.setPackage("com.google.android.apps.maps")


    if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
    } else {
        // Handle the case where Google Maps is not installed
    }
}




