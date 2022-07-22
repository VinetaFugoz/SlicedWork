package com.slicedwork.slicedwork.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.domain.usecase.candidate.GetCandidatesByIdUseCase
import com.slicedwork.slicedwork.domain.usecase.vacancy.GetVacancyByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppliedVacanciesViewModel @Inject constructor(
    private val getCandidatesByIdUseCase: GetCandidatesByIdUseCase,
    private val getVacancyByIdUseCase: GetVacancyByIdUseCase):
    ViewModel() {

    val candidatesLiveData: MutableLiveData<List<Candidate>> = MutableLiveData()
    val vacancyLiveData: MutableLiveData<Vacancy> = MutableLiveData()

    fun getCandidatesById(field: String, value: String) = viewModelScope.launch {
        getCandidatesByIdUseCase(field, value) { candidates ->
            candidatesLiveData.value = candidates
        }
    }

    fun getVacancyById(vacancyId: String) = viewModelScope.launch {
        getVacancyByIdUseCase(vacancyId) { vacancy ->
            vacancyLiveData.value = vacancy
        }
    }
}