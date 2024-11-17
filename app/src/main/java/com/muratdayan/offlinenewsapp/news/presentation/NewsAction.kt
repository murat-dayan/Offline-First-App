package com.muratdayan.offlinenewsapp.news.presentation

sealed interface NewsAction {
    data object Paginate: NewsAction
}