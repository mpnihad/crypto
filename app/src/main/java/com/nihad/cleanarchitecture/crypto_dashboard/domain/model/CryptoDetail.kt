package com.nihad.cleanarchitecture.crypto_dashboard.domain.model


//val askPrice: String,
//    val at: Long,
//    val baseAsset: String,
//    val bidPrice: String,
//    val highPrice: String,
//    val lastPrice: String,
//    val lowPrice: String,
//    val openPrice: String,
//    val quoteAsset: String,
//    val symbol: String,
//    val volume: String
data class CryptoDetail(
    val price:String="",
    val name:String="",
    val time:String="",
    val highPrice:String="",
    val lowPrice:String="",
    val lastPrice:String="",
    val currency:String="",
    val volume:String="",
    val symbol:String="",
    val differencePrice:Double=0.0
)
