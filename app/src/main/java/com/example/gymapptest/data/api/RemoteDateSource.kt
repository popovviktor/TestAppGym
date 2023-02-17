package com.example.gymapptest.data.api

import com.example.gymapptest.data.api.model.Model
import retrofit2.http.Body
import javax.inject.Inject

class RemoteDateSource @Inject constructor(private val service: Service){

    suspend fun getAll() = service.getAll()
    suspend fun post(body: Model) = service.post(body = body)

}