package com.nihad.cleanarchitecture.crypto_dashboard.data.remote
import com.nihad.cleanarchitecture.crypto_dashboard.data.remote.dto.Crypto.CryptoListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceClass {


    @GET("ticker/24hr")
    suspend fun getCryptoDetail(
        @Query("symbol") symbol:String
    ):CryptoListDTO

    @GET("tickers/24hr")
    suspend fun getCryptos():List<CryptoListDTO>
}