package com.seventh.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.seventh.compose.ui.search.Search
import com.seventh.compose.ui.splash.Splash
import com.seventh.compose.ui.web.Web

@ExperimentalPagerApi
@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { Splash(navController) }
        composable("search") { Search(navController) }
        composable("web?url={url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            Web(navController, url)
        }
    }
}