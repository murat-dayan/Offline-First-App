package com.muratdayan.offlinenewsapp.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.muratdayan.offlinenewsapp.article.presentation.ArticleScreenCore
import com.muratdayan.offlinenewsapp.core.presentation.ui.theme.OfflineNewsAppTheme
import com.muratdayan.offlinenewsapp.news.presentation.NewsScreenCore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OfflineNewsAppTheme {
                AppNavigation()
            }
        }
    }

    @Composable
    fun AppNavigation(){
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.News
        ){
            composable<Screen.News> {
                NewsScreenCore {
                    navController.navigate(Screen.Article(it))
                }
            }

            composable<Screen.Article> {backStackEntry->
                val article : Screen.Article = backStackEntry.toRoute()
                article.articleId
                ArticleScreenCore(articleId = article.articleId)
            }
        }
    }
}





