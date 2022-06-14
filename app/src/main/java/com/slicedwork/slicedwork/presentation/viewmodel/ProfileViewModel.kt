package com.slicedwork.slicedwork.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.domain.usecase.user.GetUserUseCase
import com.slicedwork.slicedwork.domain.usecase.vacancy.GetVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase, private val getVacanciesUseCase: GetVacanciesUseCase): ViewModel() {

    val vacanciesLiveData: MutableLiveData<List<Vacancy>> = MutableLiveData()
    val userLiveData: MutableLiveData<User> = MutableLiveData()

    fun getUser(userId: String) = viewModelScope.launch {
        getUserUseCase.invoke(userId) { user ->
            userLiveData.value = user
        }
    }

    fun getVacancies() = viewModelScope.launch {
        getVacanciesUseCase.invoke { vacancies ->
            vacanciesLiveData.value = vacancies
        }
    }
}