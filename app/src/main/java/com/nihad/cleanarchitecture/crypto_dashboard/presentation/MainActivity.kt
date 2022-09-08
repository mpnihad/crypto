package com.nihad.cleanarchitecture.crypto_dashboard.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.CRYPTO_API_STATE
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.CryptoViewModel
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.dashboard.components.DashbordScreen
import com.nihad.cleanarchitecture.crypto_dashboard.presentation.details.components.DetailScreen
import com.plcoding.cleanarchitecturenoteapp.ui.theme.CleanArchitectureNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: CryptoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent{

            CleanArchitectureNoteAppTheme {
                androidx.compose.material.Surface(color= MaterialTheme.colors.background) {


                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.DashboardScreen.route
                    ) {

                        composable(
                            route = Screen.DashboardScreen.route
                        ) {
                            DashbordScreen(navController = navController,viewModel)
                        }
                        composable(
                            route = Screen.DetailsScreen.route+"?symbol={symbol}",
                            arguments = listOf(
                                navArgument(
                                    name = "symbol"
                                ){
                                    type= NavType.StringType
                                    defaultValue=""
                                }
                            )
                        ) {

                            DetailScreen(navController = navController,it.arguments?.getString("symbol")?:"")
                        }

                    }
                }
            }
        }
    }

    override fun onPause() {
        viewModel.stateEvent.value=CRYPTO_API_STATE.INACTIVE
        super.onPause()

    }

    override fun onResume() {

        viewModel.stateEvent.value=CRYPTO_API_STATE.ACTIVE
        super.onResume()
    }

    override fun onDestroy() {
        viewModel.stateEvent.value=CRYPTO_API_STATE.DESTROY
        super.onDestroy()
    }

}