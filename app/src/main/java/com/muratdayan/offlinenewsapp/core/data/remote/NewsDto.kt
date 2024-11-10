package com.muratdayan.offlinenewsapp.core.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(
    val article_id:String?,
    val title:String?,
    val description: String?,
    val content:String?,
    val pubDate:String?,
    val source_name:String?,
    val image_url:String?
)