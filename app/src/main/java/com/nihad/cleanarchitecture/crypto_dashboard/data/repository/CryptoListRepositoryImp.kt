package com.nihad.cleanarchitecture.crypto_dashboard.data.repository

import com.nihad.cleanarchitecture.core.Resource
import com.nihad.cleanarchitecture.crypto_dashboard.data.remote.ServiceClass
import com.nihad.cleanarchitecture.crypto_dashboard.data.remote.dto.Crypto.CryptoListDTO
import com.nihad.cleanarchitecture.crypto_dashboard.domain.repository.CryptoRepository
import javax.inject.Inject


class CryptoListRepositoryImp @Inject constructor(
    private  val api:ServiceClass
) :CryptoRepository{
    override suspend fun getCryptoList():Resource<List<CryptoListDTO>> {
        return try {
            Resource.Success(
            api.getCryptos())
        }catch (e:Exception)
        {
            Resource.Error(e.localizedMessage)
//           emptyList<CryptoListDTO>()
        }
    }
}