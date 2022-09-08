package com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard

import com.nihad.cleanarchitecture.crypto_dashboard.domain.model.CryptoDetail

data class CryptoDetailState(
    val isLoading : Boolean=false,
    val cryptoDetail: CryptoDetail = CryptoDetail(),
    val error:String = ""
)
