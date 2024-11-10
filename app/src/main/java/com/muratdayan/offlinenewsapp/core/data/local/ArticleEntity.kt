package com.muratdayan.offlinenewsapp.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey(autoGenerate = false)
    val article_id:String,
    val title:String,
    val description: String,
    val content:String,
    val pubDate:String,
    val source_name:String,
    val image_url:String
)
