package com.nihad.cleanarchitecture.crypto_dashboard.domain.use_case.get_crypto_list

import com.nihad.cleanarchitecture.core.Resource
import com.nihad.cleanarchitecture.crypto_dashboard.data.remote.dto.Crypto.toCryptoList
import com.nihad.cleanarchitecture.crypto_dashboard.domain.model.Crypto
import com.nihad.cleanarchitecture.crypto_dashboard.domain.repository.CryptoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCryptoListUseCase @Inject constructor(
    private val repository: CryptoRepository
) {
    //only one functionality


    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(): Flow<Resource<List<Crypto>>> =flow<Resource<List<Crypto>>>{


        try{


                    emit(Resource.Loading())
                    val cryptoList = repository.getCryptoList()
                        when (cryptoList){


                            is Resource.Error -> {

                                emit(Resource.Error(cryptoList.message?:"Something went wrong"))
                            }
                            is Resource.Loading -> TODO()
                            is Resource.Success -> {
                                cryptoList.data?.let {
                                    emit(Resource.Success(it.map { it.toCryptoList()

                                    }))

                                }
                            }
                            else -> {}
                        }



                } catch (e: Exception) {
                    emit(Resource.Error(e.localizedMessage ?: "Unexpected error Occured"))
                }
                delay(5000)

            }


}