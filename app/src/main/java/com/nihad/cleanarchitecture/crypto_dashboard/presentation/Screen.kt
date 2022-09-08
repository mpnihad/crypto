package com.nihad.cleanarchitecture.crypto_dashboard.presentation

sealed class Screen(val route:String){
    object DashboardScreen:Screen("dashboard")
    object DetailsScreen:Screen("details_screen")

}
