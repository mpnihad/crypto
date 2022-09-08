package com.nihad.cleanarchitecture.di

import com.nihad.cleanarchitecture.core.Constant
import com.nihad.cleanarchitecture.crypto_dashboard.data.remote.ServiceClass
import com.nihad.cleanarchitecture.crypto_dashboard.data.repository.CryptoDetailRepositoryImp
import com.nihad.cleanarchitecture.crypto_dashboard.data.repository.CryptoListRepositoryImp
import com.nihad.cleanarchitecture.crypto_dashboard.domain.repository.CryptoDetailRepository
import com.nihad.cleanarchitecture.crypto_dashboard.domain.repository.CryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideServiceAPI():ServiceClass{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)

            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ServiceClass::class.java)

    }

    @Provides
    @Singleton
    fun providersCryptoRepository(api:ServiceClass): CryptoDetailRepository {
        return CryptoDetailRepositoryImp(api)

    }

    @Provides
    @Singleton
    fun providerCryptoRepository(api:ServiceClass):CryptoRepository{
        return CryptoListRepositoryImp(api)

    }
}