package com.taufik.jetpackcompose.compose.navigation.jetreward

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.taufik.jetpackcompose.R
import com.taufik.jetpackcompose.compose.navigation.jetreward.ui.navigation.NavigationItem
import com.taufik.jetpackcompose.compose.navigation.jetreward.ui.navigation.Screen
import com.taufik.jetpackcompose.compose.navigation.jetreward.ui.screen.cart.CartScreen
import com.taufik.jetpackcompose.compose.navigation.jetreward.ui.screen.home.HomeScreen
import com.taufik.jetpackcompose.compose.navigation.jetreward.ui.screen.profile.ProfileScreen
import com.taufik.jetpackcompose.compose.navigation.jetreward.ui.theme.JetpackComposeTheme
import androidx.compose.runtime.getValue

@Composable
fun JetRewardApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(modifier, navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Cart.route) { CartScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    navController: NavController
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_cart),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            )
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
fun BottomNavigationBarPreview() {
    JetpackComposeTheme {
        JetRewardApp()
    }
}