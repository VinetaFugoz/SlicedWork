package com.slicedwork.slicedwork.presentation.viewmodel.registeruser

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.util.enumerator.GenderEnum.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetGenderViewModel @Inject constructor() : ViewModel() {

    var checkedRadioLiveData: MutableLiveData<Int> = MutableLiveData()

    init {
        checkedRadioLiveData.value = 0
    }

    fun radioEvent(view: View) {
        checkedRadioLiveData.value = when (view.id) {
            R.id.rb_male -> MALE.ordinal
            R.id.rb_female -> FEMALE.ordinal
            else -> OTHER.ordinal
        }
    }
}