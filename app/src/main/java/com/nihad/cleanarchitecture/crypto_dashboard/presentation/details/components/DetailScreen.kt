package com.nihad.cleanarchitecture.crypto_dashboard.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nihad.cleanarchitecture.crypto_dashboard.domain.model.CryptoDetail
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.Screen
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.CryptoDetailViewModel
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.components.gotoDetailScreen
import com.plcoding.cleanarchitecturenoteapp.ui.theme.appTypography
import com.plcoding.cleanarchitecturenoteapp.ui.theme.defaultTypography

@Composable
fun DetailScreen(
    navController: NavController,
    symbol: String,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = symbol) {
        viewModel.getCryptoDetail(symbol)

    }

    val state = viewModel.state.value

    if (viewModel.isLoadingInitial.value) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = Color.Blue,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Icon(
                    Icons.Filled.ArrowBackIosNew,
                    "Back",
                    tint = Color.Black,
                    modifier = Modifier.clickable {
                        navController.navigateUp()
                    })
                Text(
                    state.cryptoDetail.name.uppercase(), modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally),
                    style = defaultTypography.h5.copy(fontWeight = FontWeight.W600)
                )
            }
            DetailCard(state.cryptoDetail)
            Text("${state.cryptoDetail.toString()}")


        }
    }
}

@Composable
fun DetailCard(cryptoDetail: CryptoDetail) {

    val itemColor = remember {
        Color((30..200).random(), (30..200).random(), (30..200).random())
    }

    Box() {

        Column {
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            Card(
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                backgroundColor = Color.Black,
                modifier = Modifier.fillMaxSize()
            ) {
                Column {

                    Spacer(modifier = Modifier.fillMaxHeight(0.1f))

                    Text("Crypto Detail", style = appTypography.h5, color = Color.White)

                    Column(
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,

                            modifier = Modifier.fillMaxWidth()
                        ) {
                            //  val price:String="",
                            //    val name:String="",
                            //    val time:String="",
                            //    val highPrice:String="",
                            //    val lowPrice:String="",
                            //    val lastPrice:String="",
                            //    val currency:String="",
                            //    val volume:String="",
                            //    val symbol:String="",
                            //    val differencePrice:Double=0.0
                            Text(
                                "Price:",
                                style = defaultTypography.body1.copy(fontWeight = FontWeight.W600),
                                color = Color.White
                            )
                            Text(cryptoDetail.price, color = Color.White)
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,

                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Currency:",
                                style = defaultTypography.body1.copy(fontWeight = FontWeight.W600),
                                color = Color.White
                            )
                            Text(cryptoDetail.currency.uppercase(), color = Color.White)
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,

                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "High price:",
                                style = defaultTypography.body1.copy(fontWeight = FontWeight.W600),
                                color = Color.White
                            )
                            Text(cryptoDetail.highPrice, color = Color.White)
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,

                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Low price:",
                                style = defaultTypography.body1.copy(fontWeight = FontWeight.W600),
                                color = Color.White
                            )
                            Text(cryptoDetail.lowPrice, color = Color.White)
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,

                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Last price:",
                                style = defaultTypography.body1.copy(fontWeight = FontWeight.W600),
                                color = Color.White
                            )
                            Text(cryptoDetail.lastPrice, color = Color.White)
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,

                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Volume:",
                                style = defaultTypography.body1.copy(fontWeight = FontWeight.W600),
                                color = Color.White
                            )
                            Text(cryptoDetail.volume, color = Color.White)
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,

                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Last Refreshed:",
                                style = defaultTypography.body1.copy(fontWeight = FontWeight.W600),
                                color = Color.White
                            )
                            Text(cryptoDetail.time, color = Color.White)
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,

                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Stats:",
                                style = defaultTypography.body1.copy(fontWeight = FontWeight.W600),
                                color = Color.White
                            )
                            Row(){
                            Text(
                                text = String.format("%.2f", cryptoDetail.differencePrice),

                                color = if (cryptoDetail.differencePrice > 0) {
                                    Color(0xff69ce96)
                                } else {
                                    Color(0xffe6481e)
                                },
                                style = defaultTypography.body1.copy(
                                    fontWeight = FontWeight.W600,
                                    color = Color.White
                                )
                            )
                            if (cryptoDetail.differencePrice > 0) {
                                Icon(
                                    Icons.Filled.ArrowDropUp,
                                    "Up",
                                    tint = Color(0xff69ce96)
                                )
                            } else {
                                Icon(
                                    Icons.Filled.ArrowDropDown,
                                    "Down",
                                    tint = Color(0xffe6481e)
                                )
                            }
                            }
                        }

                    }
                }
            }
        }
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .fillMaxHeight(0.3f)

                    .wrapContentWidth()
                    .padding(8.dp)


            ) {
                Box(
                    Modifier.fillMaxHeight()
                )
                {
                    Column(


                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(16.dp))
                            .align(Alignment.Center)
                            .background(color = itemColor)
                            .padding(vertical = 16.dp, horizontal = 32.dp)

                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    cryptoDetail.symbol.uppercase(),
                                    style = defaultTypography.h5.copy(
                                        fontWeight = FontWeight.W600,
                                        color = Color.White
                                    )
                                )
                                Text(
                                    cryptoDetail.name.uppercase(),
                                    style = defaultTypography.body1.copy(
                                        fontWeight = FontWeight.W400,
                                        color = Color.White
                                    )
                                )
                            }
                            Card(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .size(50.dp)
                                    .background(color = itemColor)
                            ) {
                                Card(
                                    shape = CircleShape, backgroundColor = itemColor,
                                    modifier = Modifier
                                        .background(Color.White)
                                        .padding(all = 5.dp)
                                ) {
                                }
                                Text(
                                    text = cryptoDetail.name.first().toString().uppercase(),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(
                                            Alignment.Center
                                        ),
                                    style = defaultTypography.h5.copy(
                                        fontWeight = FontWeight.W600,
                                        color = Color.White
                                    )
                                )

                            }

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Text(
                                text = "₹" + cryptoDetail.price,
                                modifier = Modifier.padding(end = 8.dp),
                                style = defaultTypography.h5.copy(
                                    fontWeight = FontWeight.W600,
                                    color = Color.White
                                )
                            )
                            Text(
                                text = String.format("%.2f", cryptoDetail.differencePrice),

                                color = if (cryptoDetail.differencePrice > 0) {
                                    Color(0xff69ce96)
                                } else {
                                    Color(0xffe6481e)
                                },
                                style = defaultTypography.body1.copy(
                                    fontWeight = FontWeight.W400,
                                    color = Color.White
                                )
                            )
                            if (cryptoDetail.differencePrice > 0) {
                                Icon(Icons.Filled.ArrowDropUp, "Up", tint = Color(0xff69ce96))
                            } else {
                                Icon(Icons.Filled.ArrowDropDown, "Down", tint = Color(0xffe6481e))
                            }
                        }
                    }


                }

            }

    }
}