package com.angel.daily_heros.data


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("result")
    @Expose
    var result: T? = null
}