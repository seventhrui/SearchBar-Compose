package com.seventh.compose.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.seventh.compose.theme.Colors
import com.seventh.compose.ui.search.viewmodel.SearchViewModel
import com.seventh.compose.widget.SearchBar

@Composable
fun Search(navController: NavHostController) {
    val viewModel: SearchViewModel = viewModel()

    Column(
        Modifier
            .fillMaxSize()
            .background(Colors.background)
    ) {
        SearchBar(navController = navController, viewModel = viewModel)
        viewModel.search()
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(Colors.background)
        )
        {
            itemsIndexed(viewModel.list) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("web?url=${item.getUrl()}")
                        }
                        .background(color = Color(0xFFFFFFFF))
                ) {
                    Column(
                        modifier = Modifier
                            .background(color = Color(0xFFFFFFFF))
                            .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = item.getAuthor(),
                                color = Colors.text_minor,
                                fontSize = 12.sp,
                            )

                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically),
                                text = item.getPublishTime(),
                                color = Colors.text_minor,
                                fontSize = 12.sp
                            )
                        }
                        Text(
                            modifier = Modifier
                                .padding(0.dp, 15.dp),
                            text = item.getTitleStr(),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Divider(Modifier.padding(0.dp, 2.dp, 2.dp, 0.dp), thickness = 0.5.dp)
                    }
                }
            }
        }
    }
}