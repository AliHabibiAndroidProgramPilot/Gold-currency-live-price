package com.sample.ali.goldprice.remote

import com.google.gson.Gson
import com.sample.ali.goldprice.remote.model.ErrorModel
import retrofit2.Response

object ErrorUtils {

    fun getError(response: Response<*>): String {
        var errorLog: String? = null
        val errorBody = response.errorBody()
        if (errorBody != null)
            errorLog = Gson().fromJson(errorBody.string(), ErrorModel::class.java).message
        return errorLog ?: "Connection with server was unsuccessful!"
    }

}