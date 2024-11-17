package com.muratdayan.offlinenewsapp.news.presentation

import com.muratdayan.offlinenewsapp.core.domain.Article

data class NewsState(
    val articleList: List<Article> = emptyList(),
    val nextPage: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
