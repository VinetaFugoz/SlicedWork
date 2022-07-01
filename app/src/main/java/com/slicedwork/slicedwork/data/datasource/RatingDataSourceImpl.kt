package com.slicedwork.slicedwork.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.slicedwork.slicedwork.domain.model.Rating
import javax.inject.Inject

class RatingDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : RatingDataSource {
    override suspend fun registerRating(rating: Rating, ratingCallback: (Boolean) -> Unit) {
        firebaseFirestore.collection("rating").document(rating.id).set(rating)
            .addOnSuccessListener { ratingCallback(true) }
    }

    override suspend fun updateRating(
        ratingId: String,
        rating: Double,
        ratingCallback: (Boolean) -> Unit
    ) {
        firebaseFirestore.collection("rating").document(ratingId).update("rating", rating)
            .addOnSuccessListener { ratingCallback(true) }
    }

    override suspend fun getRatings(userId: String, ratingCallback: (List<Rating>) -> Unit) {
        firebaseFirestore.collection("rating").whereEqualTo("toUserId", userId)
            .addSnapshotListener { snapshot, _ ->
                ratingCallback(convertSnapshot(snapshot))
            }
    }

    override suspend fun getRating(
        fromUserId: String,
        toUserId: String,
        ratingCallback: (Rating?) -> Unit
    ) {
        firebaseFirestore.collection("rating").whereEqualTo("fromUserId", fromUserId)
            .whereEqualTo("toUserId", toUserId).addSnapshotListener { snapshot, _ ->
                val ratings = convertSnapshot(snapshot)
                ratingCallback(if (ratings.isEmpty()) null else ratings[0])
            }
    }

    private fun convertSnapshot(snapshot: QuerySnapshot?): List<Rating> {
        val ratings: MutableList<Rating> = mutableListOf()
        val documents: List<DocumentChange> = snapshot!!.documentChanges
        for (document in documents) {
            val rating = document.document.toObject<Rating>()
            ratings.add(rating)
        }
        return ratings
    }
}