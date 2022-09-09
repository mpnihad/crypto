package com.nihad.cleanarchitecture.crypto_dashboard.data.remote.dto.Crypto

import android.text.format.DateUtils
import com.nihad.cleanarchitecture.crypto_dashboard.domain.model.Crypto
import com.nihad.cleanarchitecture.crypto_dashboard.domain.model.CryptoDetail

data class CryptoListDTO(
    val askPrice: String,
    val at: Long,
    val baseAsset: String,
    val bidPrice: String,
    val highPrice: String,
    val lastPrice: String,
    val lowPrice: String,
    val openPrice: String,
    val quoteAsset: String,
    val symbol: String,
    val volume: String
) {

}

fun CryptoListDTO.toCryptoDetail(): CryptoDetail {

    return CryptoDetail(
        symbol = this.baseAsset,
        name = this.symbol,
        currency = this.quoteAsset,
        differencePrice = (this.bidPrice.toDouble() - this.lastPrice.toDouble()),
        time = DateUtils.getRelativeTimeSpanString(
            this.at,
            System.currentTimeMillis(),
            DateUtils.SECOND_IN_MILLIS
        ).toString(),
        price = this.bidPrice,
        highPrice = this.highPrice,
        lowPrice = this.lowPrice,
        lastPrice = this.lastPrice,
        volume = this.volume,

    )

}
fun CryptoListDTO.toCryptoList(): Crypto {

    return Crypto(
        cryptoShortName = this.baseAsset,
        cryptoSymbol = this.symbol,
        currency = this.quoteAsset,
        price = this.bidPrice,
        differencePrice = (this.bidPrice.toDouble() - this.lastPrice.toDouble()),
        timeAtLastPrice = DateUtils.getRelativeTimeSpanString(
            this.at,
            System.currentTimeMillis(),
            DateUtils.SECOND_IN_MILLIS
        ).toString()
//    timeAtLastPrice = ""

    )
}