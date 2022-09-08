package com.nihad.cleanarchitecture.crypto_dashboard.domain.repository

import com.nihad.cleanarchitecture.crypto_dashboard.data.remote.dto.Crypto.CryptoListDTO

interface CryptoDetailRepository {

    suspend fun getCryptoDetails(symbol:String): CryptoListDTO
}