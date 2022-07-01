package com.slicedwork.slicedwork.domain.usecase.rating

import com.slicedwork.slicedwork.data.repository.RatingRepository
import com.slicedwork.slicedwork.domain.model.Rating
import com.slicedwork.slicedwork.domain.usecase.rating.GetRatingsUseCase
import javax.inject.Inject

class GetRatingsUseCaseImpl @Inject constructor(private val ratingRepository: RatingRepository) :
    GetRatingsUseCase {
    override suspend fun invoke(userId: String, ratingCallback: (List<Rating>) -> Unit) {
        ratingRepository.getRatings(userId) { ratings ->
            ratingCallback(ratings)
        }
    }
}