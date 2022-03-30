package com.slicedwork.slicedwork.presentation.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import com.slicedwork.slicedwork.presentation.fragment.GetNameFragment
import com.slicedwork.slicedwork.util.Validator
import com.slicedwork.slicedwork.util.enumerator.DestinationsEnum.GET_NAME_GET_BIRTHDAY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetNameViewModel @Inject constructor() : ViewModel() {

    var enabledNext: MutableLiveData<Boolean> = MutableLiveData()

    var firstName: MutableLiveData<String> = MutableLiveData()

    var lastName: MutableLiveData<String> = MutableLiveData()
    lateinit var fragment: GetNameFragment

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

    fun goToGetBirthday() =
        NavHostFragment.findNavController(fragment).navigate(GET_NAME_GET_BIRTHDAY.getDirection())

}
