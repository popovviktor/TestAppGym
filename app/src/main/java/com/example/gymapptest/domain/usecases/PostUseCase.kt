package com.example.gymapptest.domain.usecases

import com.example.gymapptest.data.Repository
import com.example.gymapptest.data.api.model.Model
import okhttp3.ResponseBody

import javax.inject.Inject

class PostUseCase @Inject constructor(
    private val repository: Repository
){
    suspend fun invoke(body: Model)= repository.post(body = body)
}