package com.slicedwork.slicedwork.domain.usecase.rating

import com.slicedwork.slicedwork.data.repository.RatingRepository
import com.slicedwork.slicedwork.domain.model.Rating
import javax.inject.Inject

class RegisterRatingUseCaseImpl @Inject constructor(private val ratingRepository: RatingRepository) :
    RegisterRatingUseCase {
    override suspend fun invoke(rating: Rating, ratingCallback: (Boolean) -> Unit) {
        ratingRepository.registerRating(rating) { isRegistered ->
            ratingCallback(isRegistered)
        }
    }
}