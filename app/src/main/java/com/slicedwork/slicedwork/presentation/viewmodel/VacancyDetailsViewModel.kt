package com.slicedwork.slicedwork.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.usecase.candidate.RegisterCandidateUseCase
import com.slicedwork.slicedwork.domain.usecase.user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacancyDetailsViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val registerCandidateUseCase: RegisterCandidateUseCase
) : ViewModel() {

    var userLiveData: MutableLiveData<User> = MutableLiveData()

    fun getUser(userId: String) = viewModelScope.launch {
        getUserUseCase.invoke(userId) { user ->
            userLiveData.value = user
        }
    }

    fun registerCandidate(candidate: Candidate) = viewModelScope.launch {
        registerCandidateUseCase.invoke(candidate)
    }
}