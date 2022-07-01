package com.slicedwork.slicedwork.domain.usecase.rating

import com.slicedwork.slicedwork.domain.model.Rating

interface RegisterRatingUseCase {
    suspend operator fun invoke(rating: Rating, ratingCallback: (Boolean) -> Unit)
}