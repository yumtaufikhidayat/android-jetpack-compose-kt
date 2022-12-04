package com.taufik.jetpackcompose.compose.interoperability.ui.list

import androidx.lifecycle.ViewModel
import com.taufik.jetpackcompose.compose.interoperability.data.remote.NewsRepository

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    fun getHeadlineNews() = newsRepository.getHeadlineNews()
    fun getBookmarkedNews() = newsRepository.getBookmarkedNews()
}