package com.taufik.jetpackcompose.ui.compose.navigation.jetreward.ui.screen.cart

import com.taufik.jetpackcompose.ui.compose.navigation.jetreward.model.OrderReward

data class CartState(
    val orderReward: List<OrderReward>,
    val totalRequiredPoint: Int
)