package com.slicedwork.slicedwork.domain.usecase.rating

import com.slicedwork.slicedwork.data.repository.RatingRepository
import com.slicedwork.slicedwork.domain.model.Rating
import javax.inject.Inject

class GetRatingUseCaseImpl @Inject constructor(private val ratingRepository: RatingRepository): GetRatingUseCase {
    override suspend fun invoke(
        fromUserId: String,
        toUserId: String,
        ratingCallback: (Rating?) -> Unit
    ) {
        ratingRepository.getRating(fromUserId, toUserId) { rating ->
            ratingCallback(rating)
        }
    }
}