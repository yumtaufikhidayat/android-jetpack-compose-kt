package com.taufik.jetpackcompose.compose.navigation.jetreward.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taufik.jetpackcompose.compose.navigation.jetreward.data.RewardRepository
import com.taufik.jetpackcompose.compose.navigation.jetreward.ui.screen.cart.CartViewModel
import com.taufik.jetpackcompose.compose.navigation.jetreward.ui.screen.detail.DetailRewardViewModel
import com.taufik.jetpackcompose.compose.navigation.jetreward.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: RewardRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            modelClass.isAssignableFrom(DetailRewardViewModel::class.java) -> DetailRewardViewModel(repository) as T
            modelClass.isAssignableFrom(CartViewModel::class.java) -> CartViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}