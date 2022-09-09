package com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.components

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.People
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nihad.cleanarchitecture.crypto_dashboard.domain.model.Crypto
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.Screen
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.CRYPTO_API_STATE
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.CryptoState
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.CryptoViewModel
import com.plcoding.cleanarchitecturenoteapp.ui.theme.appTypography
import com.plcoding.cleanarchitecturenoteapp.ui.theme.defaultTypography

@Composable
fun DashbordScreen(
    navController: NavController,
    viewModel: CryptoViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = "override") {
        viewModel.stateEvent.value = CRYPTO_API_STATE.ACTIVE
        viewModel.getCryptoList()

    }
    val state = viewModel.state.value
    val listStateRow = rememberLazyListState()
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
        Log.e("", "Error one" + state.error)
        if (viewModel.state.value.error != ""&&!viewModel.state.value.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    state.error,
                    style = defaultTypography.body1,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            Column {
                Column(modifier = Modifier.padding(top = 16.dp, start = 10.dp, end = 10.dp)) {
                    Spacer(Modifier.height(20.dp))
                    Card(
                        modifier = Modifier

                            .border(2.dp, color = Color.Black, shape = CircleShape)
                            .clip(CircleShape)

                            .size(40.dp)
                    ) {
                        Icon(Icons.Default.People, "logo")


                    }
                    Spacer(Modifier.height(20.dp))
                    Text("Hello", style = defaultTypography.h4.copy(fontWeight = FontWeight.W600))
                    Text(
                        "Welcome back!",
                        style = appTypography.body1.copy(
                            fontWeight = FontWeight.W300,
                            color = Color.Black.copy(alpha = 0.5f)
                        )
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Favorite Cryptos",
                        style = defaultTypography.body1.copy(fontWeight = FontWeight.W600)
                    )
                }
                if (viewModel.state.value.cryptoList.isNotEmpty()) {
                    FavoriteCryptos(
                        listState = listStateRow, state = state, navController = navController
                    )
                }
                Text(
                    "Live price ",
                    style = defaultTypography.body1.copy(fontWeight = FontWeight.W600),
                    modifier = Modifier.padding(all = 10.dp)
                )
                if (state.isLoading) {
                    LinearProgressIndicator(color = Color.Blue, modifier = Modifier.fillMaxWidth())
                }
                val listState = rememberLazyListState()
                LazyColumn(state = listState, modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(state.cryptoList, key = { _, it ->
                        it.cryptoSymbol
                    }) { index, crypto ->

                        val randomColor = remember {
                            Color(
                                (30..200).random(),
                                (30..200).random(),
                                (30..200).random()
                            )
                        }
                        val backgroundColor = if (index % 2 == 0) {
                            Color(0xFFF9FAFF)
                        } else {
                            Color.White
                        }
                        val invBackgroundColor = if (index % 2 != 0) {
                            Color(0xFFF9FAFF)
                        } else {
                            Color.White
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    gotoDetailScreen(navController, crypto.cryptoSymbol, viewModel)
                                }
                                .background(backgroundColor),

                            ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Card(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp, vertical = 12.dp)
                                        .size(50.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                ) {
                                    Card(
                                        shape = CircleShape, backgroundColor = randomColor,
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(invBackgroundColor)
                                            .padding(all = 8.dp)
                                    ) {
                                        Text(
                                            text = crypto.cryptoSymbol.first().toString()
                                                .uppercase(),
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .wrapContentSize(
                                                    Alignment.Center
                                                ),
                                            style = defaultTypography.body1.copy(
                                                fontWeight = FontWeight.W600,
                                                color = Color.White
                                            )
                                        )
                                    }

                                }



                                Column(modifier = Modifier.weight(0.7f)) {
                                    Text(
                                        crypto.cryptoShortName.uppercase(),
                                        style = defaultTypography.body1.copy(
                                            fontWeight = FontWeight.W400,
                                            color = randomColor
                                        )
                                    )
                                    Text(
                                        crypto.cryptoSymbol.uppercase(),
                                        style = defaultTypography.body1.copy(
                                            fontWeight = FontWeight.W400,
                                            color = randomColor.copy(alpha = 0.8f),
                                            fontSize = 10.sp
                                        )
                                    )
                                }
                                Box(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "₹" + crypto.price,

                                        color = randomColor,
                                        style = defaultTypography.body1.copy(
                                            fontWeight = FontWeight.W600,
                                            color = randomColor
                                        )
                                    )
                                }
                                Row(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = String.format("%.2f", crypto.differencePrice),

                                        color = if (crypto.differencePrice > 0) {
                                            Color(0xff69ce96)
                                        } else {
                                            Color(0xffe6481e)
                                        },
                                        style = defaultTypography.body1.copy(
                                            fontWeight = FontWeight.W600,
                                            color = randomColor
                                        )
                                    )
                                    if (crypto.differencePrice > 0) {
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
        }
    }


}

fun gotoDetailScreen(
    navController: NavController,
    cryptoSymbol: String,
    viewModel: CryptoViewModel
) {
    viewModel.stateEvent.value = CRYPTO_API_STATE.DESTROY
    navController.navigate(
        Screen.DetailsScreen.route + "?symbol=${cryptoSymbol}"
    )
}

@Composable
private fun FavoriteCryptos(
    viewModel: CryptoViewModel = hiltViewModel(),
    listState: LazyListState,
    state: CryptoState,
    navController: NavController
) {

    var favoriteList: List<Pair<Color, Crypto>> = remember {
        listOf(
            Pair(
                Color((30..200).random(), (30..200).random(), (30..200).random()),
                state.cryptoList.random()
            ),
            Pair(
                Color((30..200).random(), (30..200).random(), (30..200).random()),
                state.cryptoList.random()
            ),
            Pair(
                Color((30..200).random(), (30..200).random(), (30..200).random()),
                state.cryptoList.random()
            )
        )
    }

    return LazyRow(

        modifier = Modifier
            .fillMaxHeight(0.3f),
        state = listState,


        ) {

        itemsIndexed(items = favoriteList, itemContent = { index, item ->
            val heightAlpha: Float by animateFloatAsState(if (index - 1 != listState.firstVisibleItemIndex) 1f else 0.7f)
            val widthAlpha: Float by animateFloatAsState(if (index - 1 != listState.firstVisibleItemIndex) 0.8f else 0.7f)

            Box(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .fillParentMaxHeight()
                    .clickable(true, onClick = {
                        gotoDetailScreen(navController, item.second.cryptoSymbol, viewModel)
                    })
                    .wrapContentWidth()
                    .padding(8.dp)


            ) {
                Box(
                    Modifier.fillParentMaxHeight()
                )
                {
                    Column(


                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(16.dp))
                            .align(Alignment.Center)
                            .background(color = item.first)
                            .fillParentMaxWidth(widthAlpha)
                            .fillParentMaxHeight(heightAlpha)
                            .padding(all = 16.dp)

                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    item.second.cryptoShortName.uppercase(),
                                    style = defaultTypography.h5.copy(
                                        fontWeight = FontWeight.W600,
                                        color = Color.White
                                    )
                                )
                                Text(
                                    item.second.cryptoSymbol.uppercase(),
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
                                    .background(color = item.first)
                            ) {
                                Card(
                                    shape = CircleShape, backgroundColor = item.first,
                                    modifier = Modifier
                                        .background(Color.White)
                                        .padding(all = 5.dp)
                                ) {
                                    Text(
                                        text = item.second.cryptoSymbol.first().toString()
                                            .uppercase(),
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

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Text(
                                text = "₹" + item.second.price,
                                modifier = Modifier.padding(end = 8.dp),
                                style = defaultTypography.h5.copy(
                                    fontWeight = FontWeight.W600,
                                    color = Color.White
                                )
                            )
                            Text(
                                text = String.format("%.2f", item.second.differencePrice),

                                color = if (item.second.differencePrice > 0) {
                                    Color(0xff69ce96)
                                } else {
                                    Color(0xffe6481e)
                                },
                                style = defaultTypography.body1.copy(
                                    fontWeight = FontWeight.W400,
                                    color = Color.White
                                )
                            )
                            if (item.second.differencePrice > 0) {
                                Icon(Icons.Filled.ArrowDropUp, "Up", tint = Color(0xff69ce96))
                            } else {
                                Icon(Icons.Filled.ArrowDropDown, "Down", tint = Color(0xffe6481e))
                            }
                        }
                    }

                }
            }
        }
        )

    }
}
