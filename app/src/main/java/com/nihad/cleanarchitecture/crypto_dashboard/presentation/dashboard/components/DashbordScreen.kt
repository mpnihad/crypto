package com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.Screen
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.CRYPTO_API_STATE
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.CryptoViewModel

@Composable
fun DashbordScreen(
    navController: NavController,
    viewModel:CryptoViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val listState = rememberLazyListState()
    LazyColumn(state=listState,modifier = Modifier.fillMaxWidth()){
        items(state.cryptoList, key = {
            it.cryptoSymbol
        }){ company->

            Text(company.cryptoShortName, modifier = Modifier.clickable {
                viewModel.stateEvent.value=CRYPTO_API_STATE.DESTROY
                navController.navigate(
                    Screen.DetailsScreen.route+"?symbol=${company.cryptoSymbol}"
                )
            })

        }
    }




}