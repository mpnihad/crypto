package com.nihad.cleanarchitecture.crypto_dashboard.domain.model

import java.util.*

data class Crypto(
    val cryptoShortName:String,
    val cryptoSymbol:String,
    val currency: String,
    val differencePrice:Double,
    val price:String,
    val timeAtLastPrice:String

    )
