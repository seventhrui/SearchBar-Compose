package com.seventh.compose.ui.search.viewmodel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seventh.compose.App
import com.seventh.compose.http.Api
import com.seventh.compose.http.requestCall
import com.seventh.compose.ui.search.model.Article
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    var showLoading by mutableStateOf(false)
    var keyword by mutableStateOf("Jetpack Compose")
    var list by mutableStateOf(listOf<Article>())

    fun search() {
        viewModelScope.launch {
            showLoading = true
            val articlelist = requestCall {
                Api.get().searchKey(1, keyword)
            }
            if(articlelist.isSuccess()) {
                if (articlelist.data!!.datas.isNotEmpty()) {
                    list = articlelist.data!!.datas
                } else {
                    Toast.makeText(App.context, "未找到你想要的！", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(App.context, "网络出错了！", Toast.LENGTH_SHORT).show()
            }
            showLoading = false
        }
    }
}