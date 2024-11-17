package com.muratdayan.offlinenewsapp.news.di

import com.muratdayan.offlinenewsapp.news.presentation.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsModule = module {
    viewModel{NewsViewModel(get())}
}