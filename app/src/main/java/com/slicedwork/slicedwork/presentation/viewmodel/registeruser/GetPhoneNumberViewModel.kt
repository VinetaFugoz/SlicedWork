package com.slicedwork.slicedwork.presentation.viewmodel.registeruser

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slicedwork.slicedwork.util.validator.UserValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetPhoneNumberViewModel @Inject constructor(): ViewModel() {
    var enabledNextLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var phoneNumberLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        enabledNextLiveData.value = false
        phoneNumberLiveData.value = ""
    }

    fun afterTextChanged(editable: Editable) {
        enabledNextLiveData.value =
            UserValidator().validatePhoneNumber(phoneNumberLiveData.value.toString())
    }
}