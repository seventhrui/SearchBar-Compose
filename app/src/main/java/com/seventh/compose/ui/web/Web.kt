package com.seventh.compose.ui.web

import android.os.Build
import android.view.ViewGroup
import android.webkit.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.seventh.compose.ui.web.viewmodel.WebViewModel
import com.seventh.compose.widget.TitleBar


@Composable
fun Web(navController: NavHostController, url: String) {
    val viewModel: WebViewModel = viewModel()
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TitleBar(
            title = viewModel.title,
            onBack = {
                navController.popBackStack()
            }
        )
        Box(
            Modifier.fillMaxSize()
        ){
            AndroidView(
                { context ->
                    WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        settings.apply {
                            javaScriptEnabled = true
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                            }
                        }

                        webChromeClient = object : WebChromeClient() {
                            override fun onReceivedTitle(view: WebView?, title: String?) {
                                viewModel.title = title ?: ""
                            }

                            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                super.onProgressChanged(view, newProgress)
                                viewModel.progress = newProgress
                            }
                        }
                        loadUrl(url)
                    }
                },
                Modifier.fillMaxSize())
            if (viewModel.progress < 100) {
                LinearProgressIndicator(
                    progress = viewModel.progress / 100f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp),
                    backgroundColor = Color.Transparent
                )
            }
        }
    }
}