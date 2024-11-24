package com.muratdayan.offlinenewsapp.article.presentation

import com.muratdayan.offlinenewsapp.core.domain.Article

data class ArticleState(
    val article: Article? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
