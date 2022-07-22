package com.slicedwork.slicedwork.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.domain.usecase.candidate.GetCandidateByFieldVacancyUseCase
import com.slicedwork.slicedwork.domain.usecase.candidate.RegisterCandidateUseCase
import com.slicedwork.slicedwork.domain.usecase.user.GetUserUseCase
import com.slicedwork.slicedwork.domain.usecase.vacancy.DeleteVacancyUseCase
import com.slicedwork.slicedwork.domain.usecase.vacancy.GetVacancyByIdUseCase
import com.slicedwork.slicedwork.domain.usecase.vacancy.UpdateVacancyUseCase
import com.slicedwork.slicedwork.util.enumerator.FieldEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacancyDetailsViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getVacancyByIdUseCase: GetVacancyByIdUseCase,
    private val deleteVacancyUseCase: DeleteVacancyUseCase,
    private val registerCandidateUseCase: RegisterCandidateUseCase,
    private val updateVacancyUseCase: UpdateVacancyUseCase,
    private val getCandidateByFieldAndVacancyUseCase: GetCandidateByFieldVacancyUseCase
) : ViewModel() {

    val userLiveData: MutableLiveData<User> = MutableLiveData()
    val vacancyLiveData: MutableLiveData<Vacancy> = MutableLiveData()
    val deletedLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isCandidateRegisteredLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val candidateLiveData: MutableLiveData<Candidate?> = MutableLiveData()
    val updatedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getUser(userId: String) = viewModelScope.launch {
        getUserUseCase.invoke(userId) { user ->
            userLiveData.value = user
        }
    }

    fun getVacancyById(vacancyId: String) = viewModelScope.launch {
        getVacancyByIdUseCase.invoke(vacancyId) { vacancy ->
            vacancyLiveData.value = vacancy
        }
    }

    fun deleteVacancy(vacancyId: String) = viewModelScope.launch {
        deleteVacancyUseCase.invoke(vacancyId) { deleted ->
            deletedLiveData.value = deleted
        }
    }

    fun registerCandidate(candidate: Candidate) = viewModelScope.launch {
        registerCandidateUseCase(candidate) { isRegistered ->
            isCandidateRegisteredLiveData.value = isRegistered
        }
    }

    fun updateVacancy(isMenu: Boolean, document: String, field: FieldEnum, value: String) = viewModelScope.launch {
        updateVacancyUseCase(isMenu, document, field, value) { isUpdated ->
            updatedLiveData.value = isUpdated
        }
    }

    fun getCandidate(field: String, value: Any, vacancyId: String) = viewModelScope.launch {
        getCandidateByFieldAndVacancyUseCase(field, value, vacancyId) { candidate ->
            candidateLiveData.value = candidate
        }
    }

}