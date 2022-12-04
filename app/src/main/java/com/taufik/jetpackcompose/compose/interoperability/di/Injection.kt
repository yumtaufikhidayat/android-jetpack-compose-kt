package com.taufik.jetpackcompose.compose.interoperability.di

import android.content.Context
import com.taufik.jetpackcompose.compose.interoperability.data.local.room.NewsDatabase
import com.taufik.jetpackcompose.compose.interoperability.data.remote.NewsRepository
import com.taufik.jetpackcompose.compose.interoperability.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        return NewsRepository.getInstance(apiService, dao)
    }
}