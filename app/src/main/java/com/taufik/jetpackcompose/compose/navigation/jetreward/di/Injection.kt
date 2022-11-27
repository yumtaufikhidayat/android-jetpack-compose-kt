package com.taufik.jetpackcompose.compose.navigation.jetreward.di

import com.taufik.jetpackcompose.compose.navigation.jetreward.data.RewardRepository

object Injection {
    fun provideRepository(): RewardRepository {
        return RewardRepository.getInstance()
    }
}