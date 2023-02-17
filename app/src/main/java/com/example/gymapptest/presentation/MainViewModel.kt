package com.example.gymapptest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapptest.data.api.model.Model
import com.example.gymapptest.domain.usecases.GetAllUseCase
import com.example.gymapptest.domain.usecases.PostUseCase
import com.example.gymapptest.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject  constructor(
    private val getAll:GetAllUseCase,
    private val post:PostUseCase
):ViewModel(){
    private val _allModel = MutableLiveData<NetworkResult<Model>>()
    val allmodel:LiveData<NetworkResult<Model>>
        get() = _allModel

    init {
        getAllinfo()
    }

    fun getAllinfo(){
        viewModelScope.launch {
            getAll.invoke().let {
                _allModel.value = it
            }
        }
    }
    fun post(){
        viewModelScope.launch {
            post.invoke(body = Model())
        }
    }



}