package com.slicedwork.slicedwork.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.slicedwork.slicedwork.domain.model.Candidate
import javax.inject.Inject

class CandidateDataSourceImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore) :
    CandidateDataSource {

    override suspend fun registerCandidate(candidate: Candidate) {
        firebaseFirestore.collection("candidate").document(candidate.id).set(candidate)
    }
}