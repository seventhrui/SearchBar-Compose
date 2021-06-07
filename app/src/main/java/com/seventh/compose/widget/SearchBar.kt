package com.seventh.compose.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.statusBarsHeight
import com.seventh.compose.App
import com.seventh.compose.theme.Colors
import com.seventh.compose.ui.search.viewmodel.SearchViewModel


@Composable
fun SearchBar(navController: NavHostController, viewModel: SearchViewModel) {
    Column(
        modifier = Modifier
            .padding(16.dp, 2.dp)
            .background(color = Colors.background)
    ) {
        Spacer(
            modifier = Modifier.statusBarsHeight()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(43.dp)
                .background(
                    color = Colors.titleBar,
                    shape = MaterialTheme.shapes.small,
                )
        ) {
            if (viewModel.keyword.isEmpty()) {
                SearchHint()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            ) {
                BasicTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(15.dp, 0.dp),
                    value = viewModel.keyword,
                    onValueChange = { value ->
                        viewModel.keyword = value
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            viewModel.search()
                        }
                    )
                )
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "搜索",
                    modifier = Modifier.clickable {
                        viewModel.search()
                    }
                )
                Spacer(Modifier.width(15.dp))
            }
        }
    }
}

@Composable
private fun SearchHint() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(Modifier.width(15.dp))
        Text(
            text = "请输入查找内容",
            color = Colors.unselect
        )
    }
}

@Preview
@Composable
fun preSearchBar() {
    SearchBar(NavHostController(App.context), SearchViewModel())
}