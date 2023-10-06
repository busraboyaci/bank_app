package com.example.enqurachallenge.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.enqurachallenge.R
import com.example.searchbar.models.BankModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CircularProgressIndicatorWithDelay() {
    var isLoading by remember { mutableStateOf(true) }

    // Simulate a 3-second delay
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
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BankSearchBar(
    list: State<List<BankModel>?>,
    colors: SearchBarColors = SearchBarDefaults.colors(
        containerColor = colorResource(id = R.color.beige),
        dividerColor = colorResource(id = R.color.navy),
    )
) {
    var searchQuery by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember { mutableStateListOf("") }
    var filteredList by remember { mutableStateOf(list) }
    println("searchQuery: $searchQuery")



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
            searchQuery = ""
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

    }
}









