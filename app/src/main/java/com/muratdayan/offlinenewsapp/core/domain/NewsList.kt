package com.muratdayan.offlinenewsapp.core.domain

data class NewsList(
    val nextPage: String?,
    val articles: List<Article>
)
