package com.slicedwork.slicedwork.presentation.viewmodel.signup

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slicedwork.slicedwork.util.validator.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetPasswordViewModel @Inject constructor(): ViewModel() {
    var enabledNextLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var passwordLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        enabledNextLiveData.value = false
        passwordLiveData.value = ""
    }

    fun afterTextChanged(editable: Editable) {
        enabledNextLiveData.value =
            validatePassword(passwordLiveData.value.toString())
    }
}