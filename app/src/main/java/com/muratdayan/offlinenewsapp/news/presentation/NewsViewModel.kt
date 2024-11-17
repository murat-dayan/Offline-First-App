package com.muratdayan.offlinenewsapp.news.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratdayan.offlinenewsapp.core.domain.NewsRepository
import com.muratdayan.offlinenewsapp.core.domain.NewsResult
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    var state by mutableStateOf(NewsState())
        private set

    init {
        loadNews()
    }

    fun onAction(action: NewsAction) {
        when (action) {
            is NewsAction.Paginate -> {
                paginate()
            }
        }
    }

    private fun loadNews() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )
            newsRepository.getNews().collect{newResult->
                state = when(newResult){
                    is NewsResult.Error -> {
                        state.copy(
                            isError = true
                        )
                    }

                    is NewsResult.Success -> {
                        state.copy(
                            isError = true,
                            articleList = newResult.data?.articles ?: emptyList(),
                            nextPage = newResult.data?.nextPage
                        )
                    }
                }
            }
            state = state.copy(
                isLoading = false
            )
        }
    }

    private fun paginate() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )
            newsRepository.paginate(state.nextPage).collect{newResult->
                state = when(newResult){
                    is NewsResult.Error -> {
                        state.copy(
                            isError = true
                        )
                    }

                    is NewsResult.Success -> {
                        val articles = newResult.data?.articles ?: emptyList()
                        state.copy(
                            isError = true,
                            articleList = state.articleList + articles ,
                            nextPage = newResult.data?.nextPage
                        )
                    }
                }
            }
            state = state.copy(
                isLoading = false
            )
        }
    }

}