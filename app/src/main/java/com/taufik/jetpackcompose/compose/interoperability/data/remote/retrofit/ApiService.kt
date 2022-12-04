package com.taufik.jetpackcompose.compose.interoperability.data.remote.retrofit

import com.taufik.jetpackcompose.compose.interoperability.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?country=id&category=science")
    suspend fun getNews(@Query("apiKey") apiKey: String): NewsResponse
}