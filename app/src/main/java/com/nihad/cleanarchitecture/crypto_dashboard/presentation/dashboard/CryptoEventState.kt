package com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard

import androidx.lifecycle.MutableLiveData

data class CryptoEventState(
    val stateOfCryptoListAPICall:MutableLiveData<CRYPTO_API_STATE> = MutableLiveData(CRYPTO_API_STATE.ACTIVE)
)

enum class CRYPTO_API_STATE{
    ACTIVE,INACTIVE,DESTROY
}
