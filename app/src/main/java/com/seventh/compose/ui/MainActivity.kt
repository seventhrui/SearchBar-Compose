package com.seventh.compose.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.seventh.compose.ComposeNavigation
import com.seventh.compose.theme.ComposeTheme
import com.seventh.compose.utils.BarUtils

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.transparentStatusBar(this)
        setAndroidNativeLightStatusBar()
        setContent {
            ComposeTheme {
                ProvideWindowInsets {
                    ComposeNavigation()
                }
            }
        }
    }

    /**
     * 状态栏反色
     */
    private fun setAndroidNativeLightStatusBar() {
        val decor = window.decorView
        val isDark = resources.configuration.uiMode == 0x20
        if (!isDark) {
            decor.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decor.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

}