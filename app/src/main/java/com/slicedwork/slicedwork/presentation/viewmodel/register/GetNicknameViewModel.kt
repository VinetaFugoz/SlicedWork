package com.slicedwork.slicedwork.presentation.viewmodel.signup

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slicedwork.slicedwork.util.validator.validateNickname
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetNicknameViewModel @Inject constructor(): ViewModel() {
    var enabledNextLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var nicknameLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        enabledNextLiveData.value = false
        nicknameLiveData.value = ""
    }

    fun afterTextChanged(editable: Editable) {
        enabledNextLiveData.value =
            validateNickname(nicknameLiveData.value.toString())
    }
}