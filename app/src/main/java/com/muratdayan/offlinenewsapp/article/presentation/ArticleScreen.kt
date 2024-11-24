package com.muratdayan.offlinenewsapp.article.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.muratdayan.offlinenewsapp.core.domain.Article
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleScreenCore(
    viewModel: ArticleViewModel = koinViewModel(),
    articleId: String
){
    LaunchedEffect(true) {
        viewModel.onAction(ArticleAction.LoadArticle(articleId))
    }

    ArticleScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun ArticleScreen(
    state: ArticleState,
    onAction: (ArticleAction) -> Unit
) {

    Scaffold {paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ){
            if (state.isLoading && state.article == null){
                CircularProgressIndicator()
            }
            if (state.isError && state.article == null){
                Text(
                    text = "Can not load article",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }

            state.article?.let { article ->
                ArticleDetail(article = article)
            }
        }
    }
}

@Composable
fun ArticleDetail(
    modifier: Modifier = Modifier,
    article: Article
){

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 22.dp)
    ){
        Text(
            text = article.source_name,
            fontSize = 24.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = article.pubDate,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = article.image_url,
            contentDescription = article.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(0.4f))
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.description,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.content,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )



    }
}

















