package com.taufik.jetpackcompose.ui.compose.navigation.jetreward.ui.screen.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.taufik.jetpackcompose.R

@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.menu_cart))
    }
}