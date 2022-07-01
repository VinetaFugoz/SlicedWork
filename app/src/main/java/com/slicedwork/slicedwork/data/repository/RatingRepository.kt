package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.domain.model.Rating

interface RatingRepository {
    suspend fun registerRating(rating: Rating, ratingCallback: (Boolean) -> Unit)

    suspend fun updateRating(ratingId: String, rating: Double, ratingCallback: (Boolean) -> Unit)

    suspend fun getRatings(userId: String, ratingCallback: (List<Rating>) -> Unit)

    suspend fun getRating(fromUserId: String, toUserId: String, ratingCallback: (Rating?) -> Unit)
}