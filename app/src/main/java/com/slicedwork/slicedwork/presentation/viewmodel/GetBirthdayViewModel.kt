package com.slicedwork.slicedwork.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slicedwork.slicedwork.util.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetBirthdayViewModel @Inject constructor() : ViewModel() {

    var enabledNext: MutableLiveData<Boolean> = MutableLiveData()

    var firstName: MutableLiveData<String> = MutableLiveData()

    var lastName: MutableLiveData<String> = MutableLiveData()

    init {
        enabledNext.value = false
        firstName.value = ""
        lastName.value = ""
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        enabledNext.value =
            Validator.validateFirstName(firstName.value.toString()) &&
                    Validator.validateLastName(lastName.value.toString())
    }
}
