package com.slicedwork.slicedwork.presentation.viewmodel.registervacancy

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slicedwork.slicedwork.util.validator.VacancyValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetAddressViewModel @Inject constructor(): ViewModel() {

    var countryLiveData: MutableLiveData<String> = MutableLiveData()
    var countryErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var stateLiveData: MutableLiveData<String> = MutableLiveData()
    var stateErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var cityLiveData: MutableLiveData<String> = MutableLiveData()
    var cityErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var neighborhoodLiveData: MutableLiveData<String> = MutableLiveData()
    var neighborhoodErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var postalCodeLiveData: MutableLiveData<String> = MutableLiveData()
    var postalCodeErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var streetLiveData: MutableLiveData<String> = MutableLiveData()
    var streetErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var numberLiveData: MutableLiveData<String> = MutableLiveData()
    var numberErrorLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        countryLiveData.value = ""
        countryErrorLiveData.value = false
        stateLiveData.value = ""
        stateErrorLiveData.value = false
        cityLiveData.value = ""
        cityErrorLiveData.value = false
        neighborhoodLiveData.value = ""
        neighborhoodErrorLiveData.value = false
        postalCodeLiveData.value = ""
        postalCodeErrorLiveData.value = false
        streetLiveData.value = ""
        streetErrorLiveData.value = false
        numberLiveData.value = ""
        numberErrorLiveData.value = false
    }

    fun afterTextChanged(editable: Editable) {
        VacancyValidator().run {
            countryErrorLiveData.value = !validateCountry(countryLiveData.value.toString())
            stateErrorLiveData.value = !validateState(stateLiveData.value.toString())
            cityErrorLiveData.value = !validateCity(cityLiveData.value.toString())
            neighborhoodErrorLiveData.value = !validateNeighborhood(neighborhoodLiveData.value.toString())
            postalCodeErrorLiveData.value = !validatePostalCode(postalCodeLiveData.value.toString())
            streetErrorLiveData.value = !validateStreet(streetLiveData.value.toString())
            numberErrorLiveData.value = !validateNumber(numberLiveData.value.toString())
        }
    }
}