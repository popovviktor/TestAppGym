package com.example.gymapptest.domain.usecases

import com.example.gymapptest.data.Repository
import javax.inject.Inject

class GetAllUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun invoke() = repository.getAll()
}