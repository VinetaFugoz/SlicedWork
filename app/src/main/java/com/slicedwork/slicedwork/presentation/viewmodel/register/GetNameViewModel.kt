package com.slicedwork.slicedwork.presentation.viewmodel.signup

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slicedwork.slicedwork.util.validator.validateFirstName
import com.slicedwork.slicedwork.util.validator.validateLastName
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetNameViewModel @Inject constructor() : ViewModel() {

    var enabledNextLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var firstNameLiveData: MutableLiveData<String> = MutableLiveData()

    var lastNameLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        enabledNextLiveData.value = false
        firstNameLiveData.value = ""
        lastNameLiveData.value = ""
    }

    fun afterTextChanged(editable: Editable) {
        enabledNextLiveData.value =
            validateFirstName(firstNameLiveData.value.toString()) &&
                    validateLastName(lastNameLiveData.value.toString())
    }
}
