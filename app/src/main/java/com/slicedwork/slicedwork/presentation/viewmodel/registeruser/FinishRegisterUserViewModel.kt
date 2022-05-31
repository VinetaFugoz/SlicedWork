package com.slicedwork.slicedwork.presentation.viewmodel.registeruser

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.domain.usecase.user.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishRegisterUserViewModel @Inject constructor(private val registerUserUseCase: RegisterUserUseCase): ViewModel() {

    var registeredLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        registeredLiveData.value = false
    }

    fun registerUser(user: User) = viewModelScope.launch {
        registerUserUseCase.invoke(user) { registered ->
            registeredLiveData.value = registered
        }
    }
}