package com.taufik.jetpackcompose.compose.navigation.jetreward.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Cart: Screen("cart")
    object Profile: Screen("profile")
}
