package com.example.enqurachallenge.screens

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.enqurachallenge.BankEvent
import com.example.enqurachallenge.R
import com.example.enqurachallenge.components.CircularProgressIndicatorWithDelay
import com.example.enqurachallenge.data.viewmodel.BankListViewModel
import com.example.enqurachallenge.navigate.BankListAppRouter
import com.example.enqurachallenge.navigate.Screen
import com.example.enqurachallenge.ui.theme.Navy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankListScreen(
    viewModel: BankListViewModel,
    onEvent: (BankEvent) -> Unit,
    colors: SearchBarColors = SearchBarDefaults.colors(
        // İstenilen renkleri buradan ayarlayabilirsiniz
        containerColor = colorResource(id = R.color.beige),
        dividerColor = colorResource(id = R.color.navy),
    )
) {
    val banks = viewModel.bankList.observeAsState()
    var searchQuery by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember { mutableStateListOf("") }
//    var filteredList by remember { mutableStateOf(banks) }

    LaunchedEffect(Unit) {
        viewModel.fetchBankList()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            banks.value == null -> {
                // Loading state
                CircularProgressIndicatorWithDelay()
            }

            banks.value?.isEmpty() == true -> {
                // Empty state
                Text(text = "No banks available.")
            }

            else -> {
                SearchBar(
                    colors = colors,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.navy))
                        .padding(PaddingValues(8.dp)),
                    query = searchQuery,
                    onQueryChange = {
                        searchQuery = it
                    },
                    onSearch = {
                        items.add(searchQuery)
                        active = false
//                        searchQuery = ""
                    },
                    active = active,
                    onActiveChange = {
                        active = it
                    },
                    placeholder = {
                        Text(
                            text = "Ara",
                            color = colorResource(id = R.color.navy)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = colorResource(id = R.color.navy)
                        )
                    },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                modifier = Modifier.clickable {
                                    if (searchQuery.isNotEmpty()) {
                                        searchQuery = ""
                                    }
                                    active = false
                                },
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon",
                                tint = colorResource(id = R.color.navy)
                            )
                        }
                    },
                ) {
                    items.forEach {
                        Row(
                            modifier = Modifier.padding(all = 14.dp)
                        ) {
                            if (it.isNotEmpty()) {
                                Icon(
                                    modifier = Modifier.padding(end = 10.dp),
                                    imageVector = Icons.Default.History,
                                    contentDescription = "History Icon"
                                )
                                Text(text = it)
                            }

                        }
                    }
                }
                // Filtreleme işlemi
                val filteredBanks = if (searchQuery.isNotBlank() && banks.value != null) {
                    val filteredList = banks.value.orEmpty().filter { bank ->
                        // Arama sorgusuna göre filtreleme
                        (bank.city?.contains(searchQuery, ignoreCase = true) == true) ||
                                (bank.district?.contains(searchQuery, ignoreCase = true) == true)
                    }
                    if (filteredList.isNotEmpty()) {
                        filteredList
                    } else {
                        emptyList() // Eğer filtrelenmiş liste boş ise, boş liste döndür
                    }
                } else {
                    banks.value.orEmpty()
                }


                val banks by viewModel.bankList.observeAsState(emptyList())
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),

                    ) {
                    banks?.let {
                        items(filteredBanks.size) { index ->
                            //                        val bank = banks.value!![index]
                            val bank = filteredBanks[index]
                            println("filtered bankk: $bank")

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(vertical = 5.dp)
                                    .clickable(onClick = {
                                        onEvent(BankEvent.SelectBank(bank))
                                        BankListAppRouter.navigateTo(Screen.SelectedBankInfoScreen)
                                    }),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Card(
                                    modifier = Modifier
                                        .weight(0.1f)
                                        .clip(shape = RoundedCornerShape(15.dp))
                                        .background(colorResource(id = R.color.beige))
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween, // Aligns items horizontally with space between
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Image(
                                            painter = painterResource(
                                                id = R.drawable.bank_image
                                            ),
                                            contentDescription = null, // Iconların genellikle content description'ı olmaz
                                            modifier = Modifier
                                                .size(48.dp)
                                                .padding(end = 10.dp)
                                        )
                                        Column{
                                            Text(
                                                text = "${bank.city}, ",
                                                fontSize = 20.sp,
                                                textAlign = TextAlign.Start,
                                                color = Navy,
                                                modifier = Modifier,
                                                style = TextStyle(
                                                    fontSize = 24.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    fontStyle = FontStyle.Normal
                                                ),
                                            )

                                            Text(
                                                text = "${bank.bankAddress}",
                                                fontSize = 15.sp,
                                                textAlign = TextAlign.Start,
                                                color = Navy,
                                                modifier = Modifier,
                                                style = TextStyle(
                                                    fontSize = 24.sp,
                                                    fontWeight = FontWeight.Normal,
                                                    fontStyle = FontStyle.Normal
                                                )
                                            )
                                        }


                                    }
                                }
                            }
                            //                        bank.bankBranch?.let { Text(text = it) } // Assuming 'dcAddressName' is a property of BankModel
                            //                        bank.bankAddress?.let { Text(text = it) }
                            //                        Divider() // Add a divider between items
                        }
                    }
                }
            }
        }
    }
}


