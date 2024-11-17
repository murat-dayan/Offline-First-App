package com.muratdayan.offlinenewsapp.news.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.W
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.muratdayan.offlinenewsapp.core.domain.Article
import com.muratdayan.offlinenewsapp.core.presentation.ui.theme.OfflineNewsAppTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsScreenCore(
    viewModel: NewsViewModel = koinViewModel(),
    onArticleClick: (String)->Unit
){
    NewsScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
        onArticleClick = onArticleClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    state: NewsState,
    onAction: (NewsAction) -> Unit,
    onArticleClick: (String)->Unit
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    Scaffold (
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = "The News",
                        fontSize = 33.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                windowInsets = WindowInsets(
                    top = 50.dp,
                    bottom = 8.dp
                )
            )
        }
    ){innerPading->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPading)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            if(state.isLoading && state.articleList.isEmpty()){
                CircularProgressIndicator()
            }
            if(state.isError && state.articleList.isEmpty()){
                Text(
                    text = "Can't Load News",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
            }

            if(state.articleList.isNotEmpty()){
                val listState = rememberLazyListState()
                val shouldPaginate = remember {
                    derivedStateOf {
                        val totalItemsCount = listState.layoutInfo.totalItemsCount
                        val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo
                            .lastOrNull()?.index ?: 0
                        lastVisibleIndex == totalItemsCount - 1 && !state.isLoading
                    }
                }

                LaunchedEffect(key1 = listState) {
                    snapshotFlow { shouldPaginate.value }.distinctUntilChanged()
                        .filter { it }
                        .collect{onAction(NewsAction.Paginate)}
                }

                LazyColumn (
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 8.dp),
                    state = listState
                ){
                    itemsIndexed(
                        items = state.articleList,
                        key = {_,article-> article.article_id}
                    ){index,article->
                        ArticleItem(
                            article = article,
                            onArticleClick=onArticleClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onArticleClick: (String)->Unit
){
    Column (
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onArticleClick(article.article_id)
            }
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ){
        Text(
            text = article.source_name,
            fontSize = 22.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.title,
            fontSize = 18.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        AsyncImage(
            model = article.image_url,
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = article.description,
            fontSize = 17.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = article.pubDate,
            fontSize = 15.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(0.7f))
    )
}


@Preview(showBackground = true)
@Composable
fun NewsScreenPreview(){
    OfflineNewsAppTheme {
        NewsScreen(
            state = NewsState(),
            onAction = {},
            onArticleClick = {}
        )
    }
}