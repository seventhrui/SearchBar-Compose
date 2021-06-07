package com.seventh.compose.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.accompanist.insets.statusBarsHeight

import com.seventh.compose.R
import com.seventh.compose.theme.Colors

@Composable
fun TitleBar(
    title: String,
    onBack: (() -> Unit)? = null,
    @DrawableRes icon: Int? = null,
    onIconClick: (() -> Unit)? = null
) {
    Column(modifier = Modifier.background(color = Colors.titleBar)) {
        Spacer(
            modifier = Modifier.statusBarsHeight()
        )
        Row(
            Modifier
                .fillMaxWidth()
                .height(43.dp)
                .background(Colors.titleBar)
        ) {
            if (onBack != null) {
                Icon(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .clickable {
                            onBack.invoke()
                        }
                        .size(48.dp)
                        .padding(14.dp),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "返回",
                    tint = Colors.text_h1
                )
            }
            Text(
                text = title,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = 16.dp, end = 16.dp)
                    .weight(1f),
                color = Colors.text_h1,
                fontSize = 17.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (icon != null) {
                Icon(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .clickable {
                            onIconClick?.invoke()
                        }
                        .size(48.dp)
                        .padding(14.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    tint = Colors.text_h1
                )
            }
        }
    }
}