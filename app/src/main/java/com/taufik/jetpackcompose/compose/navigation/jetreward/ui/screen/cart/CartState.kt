package com.taufik.jetpackcompose.compose.navigation.jetreward.ui.screen.cart

import com.taufik.jetpackcompose.compose.navigation.jetreward.model.OrderReward

data class CartState(
    val orderReward: List<OrderReward>,
    val totalRequiredPoint: Int
)