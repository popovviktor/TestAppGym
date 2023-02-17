package com.example.gymapptest.utils

import com.example.gymapptest.data.api.model.Model
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(api: suspend ()-> Response<T>):NetworkResult<T>{
        try {
            val response = api()
            if (response.isSuccessful){
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }?: return errorMessage("Body is empty")
            }else{
                return errorMessage("${response.code()}"+"${response.message()}")
            }
        }catch (ex:Exception){
            return errorMessage(ex.message.toString())
        }
    }

    private fun <T> errorMessage(ex:String): NetworkResult.Error<T> =
        NetworkResult.Error(data = null, message = "Api call failed $ex")
}