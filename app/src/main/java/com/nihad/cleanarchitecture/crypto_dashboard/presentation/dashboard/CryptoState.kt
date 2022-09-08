package com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard

import com.nihad.cleanarchitecture.crypto_dashboard.domain.model.Crypto

data class CryptoState(
    var isLoading : Boolean=false,
    val cryptoList:List<Crypto> = emptyList(),
    val error:String = ""
)