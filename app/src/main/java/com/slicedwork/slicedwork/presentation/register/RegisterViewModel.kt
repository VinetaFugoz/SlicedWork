package com.slicedwork.slicedwork.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.model.Response
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.usecase.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val useCases: UseCases) : ViewModel() {

    private val _isUserRegisterLiveData = MutableLiveData<Response<Void?>>(Response.Success(null))
    val isUserRegisterLiveData: LiveData<Response<Void?>> get() = _isUserRegisterLiveData

    fun registerUser(user: User) {
        viewModelScope.launch {
            useCases.registerUser(user)
        }
    }
}