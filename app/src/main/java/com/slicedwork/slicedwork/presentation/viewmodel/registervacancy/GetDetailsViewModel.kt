package com.slicedwork.slicedwork.presentation.viewmodel.registervacancy

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slicedwork.slicedwork.util.validator.UserValidator
import com.slicedwork.slicedwork.util.validator.VacancyValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetDetailsViewModel @Inject constructor() : ViewModel() {

    var taskLiveData: MutableLiveData<String> = MutableLiveData()
    var taskErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var occupationAreaLiveData: MutableLiveData<String> = MutableLiveData()
    var occupationAreaErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var priceLiveData: MutableLiveData<String> = MutableLiveData()
    var priceErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        taskLiveData.value = ""
        taskErrorLiveData.value = true
        occupationAreaLiveData.value = ""
        occupationAreaErrorLiveData.value = true
        priceLiveData.value = ""
        priceErrorLiveData.value = true
    }

    fun afterTextChanged(editable: Editable) {
        VacancyValidator().run {
            taskErrorLiveData.value = !validateTask(taskLiveData.value.toString())
            occupationAreaErrorLiveData.value =
                !validateOccupationArea(occupationAreaLiveData.value.toString())
            priceErrorLiveData.value = !validatePrice(priceLiveData.value.toString())

        }
    }
}