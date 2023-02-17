package com.example.gymapptest.data

import com.example.gymapptest.data.api.RemoteDateSource
import com.example.gymapptest.data.api.model.Model
import com.example.gymapptest.utils.BaseApiResponse
import com.example.gymapptest.utils.NetworkResult
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDateSource: RemoteDateSource):BaseApiResponse() {
    suspend fun getAll():NetworkResult<Model>{
        return safeApiCall { remoteDateSource.getAll() }
    }
    suspend fun post(body: Model):NetworkResult<Model>{
        return safeApiCall { remoteDateSource.post(body = body) }
    }
}