package com.slicedwork.slicedwork.presentation.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.presentation.fragment.GetNameFragment
import com.slicedwork.slicedwork.presentation.fragment.GetNameFragmentDirections
import com.slicedwork.slicedwork.util.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
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

    fun nextEvent(view: View) {
        val user = createUser()
        goToGetBirthday(view, user)
    }

    private fun createUser() = User(
        uuid = UUID.randomUUID().toString(),
        firstName = this.firstName.value.toString(),
        this.lastName.value.toString()
    )

    fun goToGetBirthday(view: View, user: User) =
        view.findNavController()
            .navigate(GetNameFragmentDirections.actionGetNameFragmentToGetBirthdayFragment(user))

}
