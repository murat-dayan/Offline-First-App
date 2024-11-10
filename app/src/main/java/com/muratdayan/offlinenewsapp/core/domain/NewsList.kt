package com.muratdayan.offlinenewsapp.core.domain

data class NewsList(
    val nextPage: String?,
    val results: List<Article>
)
