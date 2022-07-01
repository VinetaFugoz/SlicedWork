package com.slicedwork.slicedwork.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.domain.model.Rating
import com.slicedwork.slicedwork.domain.usecase.rating.GetRatingUseCase
import com.slicedwork.slicedwork.domain.usecase.rating.RegisterRatingUseCase
import com.slicedwork.slicedwork.domain.usecase.rating.UpdateRatingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val registerRatingUseCase: RegisterRatingUseCase,
    private val updateRatingUseCase: UpdateRatingUseCase,
    private val getRatingUseCase: GetRatingUseCase
) : ViewModel() {

    val isRegisteredLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isUpdatedLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val ratingLiveData: MutableLiveData<Rating> = MutableLiveData()

    fun registerRatingUseCase(rating: Rating) = viewModelScope.launch {
        registerRatingUseCase.invoke(rating) { isRegistered ->
            isRegisteredLiveData.value = isRegistered
        }
    }

    fun updateRatingUseCase(ratingId: String, rating: Double) = viewModelScope.launch {
        updateRatingUseCase.invoke(ratingId, rating) { isUpdated ->
            isUpdatedLiveData.value = isUpdated
        }
    }

    fun getRatingUseCase(fromUserId: String, toUserId: String) = viewModelScope.launch {
        getRatingUseCase.invoke(fromUserId, toUserId) { rating ->
            ratingLiveData.value = rating
        }
    }
}