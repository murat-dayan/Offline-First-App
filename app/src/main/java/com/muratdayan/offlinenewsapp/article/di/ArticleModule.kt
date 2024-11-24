package com.muratdayan.offlinenewsapp.article.di

import com.muratdayan.offlinenewsapp.article.presentation.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articleModule = module {
    viewModel{ArticleViewModel(get())}
}