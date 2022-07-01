package com.slicedwork.slicedwork.domain.usecase.rating

import com.slicedwork.slicedwork.domain.model.Rating

interface GetRatingUseCase {
    suspend operator fun invoke(
        fromUserId: String,
        toUserId: String,
        ratingCallback: (Rating?) -> Unit
    )
}