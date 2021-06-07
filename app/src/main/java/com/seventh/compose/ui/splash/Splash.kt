package com.seventh.compose.ui.splash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.delay


@Composable
fun Splash(navController: NavHostController) {
    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = rememberCoilPainter("https://www.wanandroid.com/resources/image/pc/logo.png"),
            contentDescription = "Splash Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Inside
        )

        var timeInSec = 3
        var trigger by remember { mutableStateOf(timeInSec) }

        val elapsed by animateIntAsState(
            targetValue = trigger * 1000,
            animationSpec = tween(timeInSec * 1000, easing = LinearEasing)
        )

        DisposableEffect(Unit) {
            trigger = 0
            onDispose {}
        }

        LaunchedEffect(Unit) {
            delay(3000)
            navController.backQueue.removeAll(navController.backQueue)      // 清除队列 需要改进
            navController.navigate("search")
        }

        Text(
            text = "跳过("+(elapsed/1000).toString()+"s)",
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.TopEnd)
                .padding(5.dp, 10.dp, 5.dp, 10.dp)
                .clickable {
                    navController.backQueue.removeAll(navController.backQueue)      // 清除队列 需要改进
                    navController.navigate("search")
                }
        )
    }
}

@Preview
@Composable
fun PreContent() {
    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = rememberCoilPainter("https://media.ifanrusercontent.com/user_files/enterprise/cd/1a/cd1ac19257bb1ff70cc906dedf0cc8c7ddd422b9-aae852b95df14852b56c37a07c3f96d81ad0d1a5.png"),
            contentDescription = "Splash Image",
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x00ff00)),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = "跳过(3s)",
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.TopEnd)
                .padding(5.dp, 10.dp, 5.dp, 10.dp)
                .clickable {

                }
        )
    }
}