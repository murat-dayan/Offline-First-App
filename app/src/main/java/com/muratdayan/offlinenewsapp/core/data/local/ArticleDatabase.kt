package com.muratdayan.offlinenewsapp.core.data.local

import androidx.room.Database

@Database(entities = [ArticleEntity::class], version = 1)
abstract class ArticleDatabase {
    abstract val dao : ArticlesDao
}