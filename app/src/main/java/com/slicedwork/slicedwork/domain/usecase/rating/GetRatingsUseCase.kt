package com.slicedwork.slicedwork.domain.usecase.rating

import com.slicedwork.slicedwork.domain.model.Rating

interface GetRatingsUseCase {
    suspend operator fun invoke(userId: String, ratingCallback: (List<Rating>) -> Unit)
}