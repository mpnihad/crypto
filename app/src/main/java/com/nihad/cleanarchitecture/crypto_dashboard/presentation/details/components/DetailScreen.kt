package com.nihad.cleanarchitecture.crypto_dashboard.presentation.details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.CryptoDetailViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    symbol:String,
    viewModel:CryptoDetailViewModel= hiltViewModel()
) {
    LaunchedEffect(key1 = symbol){
        viewModel.getCryptoDetail(symbol)

    }

    val state = viewModel.state.value


            Text("${state.cryptoDetail.toString()}")







}