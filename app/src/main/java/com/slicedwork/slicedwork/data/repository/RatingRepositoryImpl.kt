package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.data.datasource.RatingDataSource
import com.slicedwork.slicedwork.domain.model.Rating
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(private val ratingDataSource: RatingDataSource) :
    RatingRepository {
    override suspend fun registerRating(rating: Rating, ratingCallback: (Boolean) -> Unit) {
        ratingDataSource.registerRating(rating) { isRegistered ->
            ratingCallback(isRegistered)
        }
    }

    override suspend fun updateRating(
        ratingId: String,
        rating: Double,
        ratingCallback: (Boolean) -> Unit
    ) {
        ratingDataSource.updateRating(ratingId, rating) { isUpdated -> ratingCallback(isUpdated) }
    }

    override suspend fun getRatings(userId: String, ratingCallback: (List<Rating>) -> Unit) {
        ratingDataSource.getRatings(userId) { ratings ->
            ratingCallback(ratings)
        }
    }

    override suspend fun getRating(
        fromUserId: String,
        toUserId: String,
        ratingCallback: (Rating?) -> Unit
    ) {
        ratingDataSource.getRating(fromUserId, toUserId) { rating -> ratingCallback(rating) }
    }
}