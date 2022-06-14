package com.slicedwork.slicedwork.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.domain.usecase.user.GetUserUseCase
import com.slicedwork.slicedwork.domain.usecase.vacancy.DeleteVacancyUseCase
import com.slicedwork.slicedwork.domain.usecase.vacancy.GetVacancyByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacancyDetailsViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getVacancyByIdUseCase: GetVacancyByIdUseCase,
    private val deleteVacancyUseCase: DeleteVacancyUseCase
) : ViewModel() {

    val userLiveData: MutableLiveData<User> = MutableLiveData()
    val vacancyLiveData: MutableLiveData<Vacancy> = MutableLiveData()
    val deletedLiveData: MutableLiveData<Boolean> = MutableLiveData()

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
}