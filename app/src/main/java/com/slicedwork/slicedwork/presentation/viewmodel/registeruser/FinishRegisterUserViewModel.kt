package com.slicedwork.slicedwork.presentation.viewmodel.registeruser

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

    fun registerUser(user: User) = viewModelScope.launch {
        registerUserUseCase.invoke(user)
    }
}