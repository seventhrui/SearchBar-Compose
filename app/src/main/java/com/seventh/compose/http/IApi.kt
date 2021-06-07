package com.seventh.compose.http

import com.seventh.compose.ui.search.model.ArticleList
import retrofit2.http.*

interface IApi {

    @POST("article/query/{page}/json")
    suspend fun searchKey(@Path("page") page: Int, @Query("k") keyword: String): BaseResponse<ArticleList>
}