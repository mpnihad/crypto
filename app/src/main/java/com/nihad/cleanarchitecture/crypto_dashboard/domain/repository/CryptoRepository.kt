package com.nihad.cleanarchitecture.crypto_dashboard.domain.repository

import com.nihad.cleanarchitecture.core.Resource
import com.nihad.cleanarchitecture.crypto_dashboard.data.remote.dto.Crypto.CryptoListDTO

interface CryptoRepository {
    suspend fun getCryptoList():Resource<List<CryptoListDTO>>
}