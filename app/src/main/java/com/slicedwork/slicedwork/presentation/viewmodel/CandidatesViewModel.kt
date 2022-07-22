package com.slicedwork.slicedwork.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.usecase.candidate.GetCandidatesByIdUseCase
import com.slicedwork.slicedwork.domain.usecase.candidate.UpdateCandidateUseCase
import com.slicedwork.slicedwork.domain.usecase.user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandidatesViewModel @Inject constructor(
    private val getCandidatesByIdUseCase: GetCandidatesByIdUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateCandidateUseCase: UpdateCandidateUseCase
) : ViewModel() {

    val candidatesLiveData: MutableLiveData<List<Candidate>> = MutableLiveData()
    val userLiveData: MutableLiveData<User> = MutableLiveData()
    val isUpdatedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getCandidatesById(field: String, value: String) = viewModelScope.launch {
        getCandidatesByIdUseCase(field, value) { candidates ->
            candidatesLiveData.value = candidates
        }
    }

    fun getUser(userId: String) = viewModelScope.launch {
        getUserUseCase(userId) { user ->
            userLiveData.value = user
        }
    }

    fun updateCandidate(document: String, field: String, value: Int) = viewModelScope.launch {
        updateCandidateUseCase(document, field, value) { isUpdated ->
            isUpdatedLiveData.value = isUpdated
        }
    }
}