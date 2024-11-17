package com.muratdayan.offlinenewsapp.news.presentation

import androidx.lifecycle.ViewModel
import com.muratdayan.offlinenewsapp.core.domain.NewsRepository

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
}