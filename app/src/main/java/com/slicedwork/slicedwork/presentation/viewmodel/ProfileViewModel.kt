package com.slicedwork.slicedwork.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.model.Rating
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.domain.usecase.rating.GetRatingsUseCase
import com.slicedwork.slicedwork.domain.usecase.user.GetUserUseCase
import com.slicedwork.slicedwork.domain.usecase.vacancy.GetVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val getRatingsUseCase: GetRatingsUseCase
) : ViewModel() {

    val userLiveData: MutableLiveData<User> = MutableLiveData()
    val vacanciesLiveData: MutableLiveData<List<Vacancy>> = MutableLiveData()
    val ratingsLiveData: MutableLiveData<List<Rating>> = MutableLiveData()

    fun getUser(userId: String) = viewModelScope.launch {
        getUserUseCase.invoke(userId) { user ->
            userLiveData.value = user
        }
    }

    fun getVacancies(userId: String) = viewModelScope.launch {
        getVacanciesUseCase.invoke(userId = userId) { vacancies ->
            vacanciesLiveData.value = vacancies
        }
    }

    fun getRatings(userId: String) = viewModelScope.launch {
        getRatingsUseCase.invoke(userId) { ratings ->
            ratingsLiveData.value = ratings
        }
    }
}