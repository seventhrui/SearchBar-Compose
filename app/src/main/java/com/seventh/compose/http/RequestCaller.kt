package com.seventh.compose.http

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun <T> requestCall(crossinline call: suspend CoroutineScope.() -> BaseResponse<T>): BaseResponse<T> {
    return withContext(Dispatchers.IO) {
        val res: BaseResponse<T>

        try {
            res = call()
        } catch (e: Exception) {
            return@withContext BaseResponse.fromException(e)
        }
        return@withContext res
    }
}