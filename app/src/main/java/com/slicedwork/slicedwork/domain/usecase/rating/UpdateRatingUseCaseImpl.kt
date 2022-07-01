package com.slicedwork.slicedwork.domain.usecase.rating

import com.slicedwork.slicedwork.data.repository.RatingRepository
import javax.inject.Inject

class UpdateRatingUseCaseImpl @Inject constructor(private val ratingRepository: RatingRepository) :
    UpdateRatingUseCase {
    override suspend fun invoke(
        ratingId: String,
        rating: Double,
        ratingCallback: (Boolean) -> Unit
    ) {
        ratingRepository.updateRating(ratingId, rating) { isUpdated ->
            ratingCallback(isUpdated)
        }
    }
}