package com.nihad.cleanarchitecture.crypto_dashboard.domain.use_case.get_crypto_details

import com.nihad.cleanarchitecture.core.Resource
import com.nihad.cleanarchitecture.crypto_dashboard.data.remote.dto.Crypto.toCryptoDetail
import com.nihad.cleanarchitecture.crypto_dashboard.domain.model.CryptoDetail
import com.nihad.cleanarchitecture.crypto_dashboard.domain.repository.CryptoDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CryptoDetailUseCase @Inject constructor(
    private val repository: CryptoDetailRepository
) {
    //only one functionality


    operator fun invoke(symbol:String): Flow<Resource<CryptoDetail>> = flow<Resource<CryptoDetail>>{

        try {

            emit(Resource.Loading())
            val crypto = repository.getCryptoDetails(symbol).toCryptoDetail()
            emit(Resource.Success(crypto))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error Occured"))
        }


    }

}