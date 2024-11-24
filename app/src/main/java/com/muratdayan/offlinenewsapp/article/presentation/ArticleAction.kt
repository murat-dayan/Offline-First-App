package com.muratdayan.offlinenewsapp.article.presentation

sealed interface ArticleAction {
    data class LoadArticle(val articleId: String) : ArticleAction
}