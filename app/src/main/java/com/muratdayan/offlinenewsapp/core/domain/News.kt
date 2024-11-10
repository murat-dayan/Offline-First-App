package com.muratdayan.offlinenewsapp.core.domain

import kotlinx.serialization.Serializable


data class News(
    val article_id:String,
    val title:String,
    val description: String,
    val content:String,
    val pubDate:String,
    val source_name:String,
    val image_url:String
)
