package com.slicedwork.slicedwork.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.domain.usecase.vacancy.GetVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncedViewModel @Inject constructor(private val getVacanciesUseCase: GetVacanciesUseCase) :
    ViewModel() {

    val vacanciesLiveData: MutableLiveData<List<Vacancy>> = MutableLiveData()

    init {
        vacanciesLiveData.value = listOf()
    }

    fun getVacancies() = viewModelScope.launch {
        getVacanciesUseCase.invoke(userId = FirebaseAuth.getInstance().currentUser!!.uid) { vacancies ->
            vacanciesLiveData.value = vacancies
        }
    }

}