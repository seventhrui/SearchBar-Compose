package com.seventh.compose.ui.search.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

data class ArticleList(
    @SerializedName("curPage") val curPage: Int = 0,
    @SerializedName("datas") val datas: List<Article> = listOf(),
    @SerializedName("offset") val offset: Int = 0,
    @SerializedName("over") val over: Boolean = false,
    @SerializedName("pageCount") val pageCount: Int = 0,
    @SerializedName("size") val size: Int = 0,
    @SerializedName("total") val total: Int = 0
)

data class Article(
    @SerializedName("link") val link: String = "",
    @SerializedName("author") private val author: String = "",
    @SerializedName("desc") val desc: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("publishTime") val publishTime: Long,
    @SerializedName("shareUser") val shareUser: String = "",
) {
    fun getPublishTime(): String {
        return SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(publishTime)
    }
    fun getAuthor(): String {
        return if (author.isNotEmpty()) author else shareUser
    }
    fun getTitleStr(): String {
        val regex = Regex("<[^>]+>")
        val foundMatches = regex.findAll(title)
        var formatCookMethods: String = title
        foundMatches.forEach { result ->
            formatCookMethods =
                formatCookMethods.replace(result.value, "")
        }
        return formatCookMethods
    }
    fun getUrl(): String {
        return if (link.startsWith("http")) link else "https://www.wanandroid.com$link"
    }
}