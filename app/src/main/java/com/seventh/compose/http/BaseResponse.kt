package com.seventh.compose.http

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("errorCode") var status: Int = 0,
    @SerializedName("errorMsg") var message: String? = "",
    @SerializedName("data") var data: T? = null
) {
    fun isSuccess(): Boolean {
        return status == 0 && data != null
    }

    companion object {

        fun <T> fromException(e: Throwable): BaseResponse<T> {
            return BaseResponse(-1, e.message)
        }
    }
}