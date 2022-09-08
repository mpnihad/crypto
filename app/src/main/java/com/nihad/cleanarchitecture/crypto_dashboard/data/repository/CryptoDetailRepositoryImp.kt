package com.nihad.cleanarchitecture.crypto_dashboard.data.repository

import com.nihad.cleanarchitecture.crypto_dashboard.data.remote.ServiceClass
import com.nihad.cleanarchitecture.crypto_dashboard.data.remote.dto.Crypto.CryptoListDTO
import com.nihad.cleanarchitecture.crypto_dashboard.domain.repository.CryptoDetailRepository
import javax.inject.Inject

class CryptoDetailRepositoryImp @Inject constructor(
    private  val api:ServiceClass
) :CryptoDetailRepository{
    override suspend fun getCryptoDetails(symbol: String): CryptoListDTO {
        return api.getCryptoDetail(symbol = symbol)
    }
}