package com.example.gymapptest.data.api


import com.example.gymapptest.data.api.model.Model
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Service {
    @GET("get_v3/?club_id=2")
    suspend fun getAll():Response<Model>
    @POST("post")
    suspend fun post(@Body body: Model):Response<Model>




}