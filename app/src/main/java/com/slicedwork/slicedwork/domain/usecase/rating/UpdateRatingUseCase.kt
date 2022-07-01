package com.slicedwork.slicedwork.domain.usecase.rating

interface UpdateRatingUseCase {
    suspend operator fun invoke(ratingId: String, rating: Double, ratingCallback: (Boolean) -> Unit)
}