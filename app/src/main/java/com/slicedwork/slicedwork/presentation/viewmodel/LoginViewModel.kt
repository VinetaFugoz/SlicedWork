package com.slicedwork.slicedwork.presentation.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.usecase.user.LoginUserUseCase
import com.slicedwork.slicedwork.util.validator.UserValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUserUseCase: LoginUserUseCase) :
    ViewModel() {
    var enabledNextLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var emailLiveData: MutableLiveData<String> = MutableLiveData()
    var passwordLiveData: MutableLiveData<String> = MutableLiveData()
    var loggedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        enabledNextLiveData.value = false
        emailLiveData.value = ""
        passwordLiveData.value = ""
        loggedLiveData.value = false
    }

    fun afterTextChanged(editable: Editable) {
        UserValidator().run {
            enabledNextLiveData.value =
                validateEmail(emailLiveData.value.toString()) &&
                        validatePassword(passwordLiveData.value.toString())
        }
    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        loginUserUseCase.invoke(email, password) { logged ->
            loggedLiveData.value = logged
        }
    }
}