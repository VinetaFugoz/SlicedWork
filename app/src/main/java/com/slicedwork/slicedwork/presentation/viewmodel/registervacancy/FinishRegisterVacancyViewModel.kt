package com.slicedwork.slicedwork.presentation.viewmodel.registervacancy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.domain.usecase.vacancy.RegisterVacancyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishRegisterVacancyViewModel @Inject constructor(private val registerVacancyUseCase: RegisterVacancyUseCase) :
    ViewModel() {

    val isRegisteredLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun registerVacancy(vacancy: Vacancy) = viewModelScope.launch {
        registerVacancyUseCase.invoke(vacancy) { isRegistered ->
            isRegisteredLiveData.value = isRegistered
        }
    }
}