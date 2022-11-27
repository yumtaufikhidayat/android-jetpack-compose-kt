package com.taufik.jetpackcompose.ui.compose.navigation.jetreward.di

import com.taufik.jetpackcompose.ui.compose.navigation.jetreward.data.RewardRepository

object Injection {
    fun provideRepository(): RewardRepository {
        return RewardRepository.getInstance()
    }
}