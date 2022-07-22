package com.slicedwork.slicedwork.data.datasource

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.Rating
import com.slicedwork.slicedwork.domain.model.Vacancy
import javax.inject.Inject

class CandidateDataSourceImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore) :
    CandidateDataSource {

    override suspend fun registerCandidate(
        candidate: Candidate,
        candidateCallback: (Boolean) -> Unit
    ) {
        firebaseFirestore.collection("candidate").document(candidate.id).set(candidate)
            .addOnSuccessListener {
                candidateCallback(true)
            }
    }

    override suspend fun getCandidatesByIdCandidate(
        field: String,
        value: String,
        candidateCallback: (List<Candidate>) -> Unit) {
        firebaseFirestore.collection("candidate").whereEqualTo(field, value)
            .addSnapshotListener { snapshot, _ ->
                candidateCallback(convertSnapshot(snapshot))
            }
    }

    override suspend fun getCandidateByUserAndVacancy(field: String, value: Any, vacancyId: String, candidateCallback: (Candidate?) -> Unit) {
        firebaseFirestore.collection("candidate")
            .whereEqualTo(field, value)
            .whereEqualTo("vacancyId", vacancyId).get().addOnSuccessListener { snapshot ->
                val candidates = convertSnapshot(snapshot)
                candidateCallback(if (candidates.isEmpty()) null else candidates[0])
            }
    }

    override suspend fun updateCandidate(
        document: String,
        field: String,
        value: Any,
        candidateCallback: (Boolean) -> Unit
    ) {
        firebaseFirestore.collection("candidate").document(document).update(field, value)
            .addOnSuccessListener {
                candidateCallback(true)
            }
    }

    private fun convertSnapshot(snapshot: QuerySnapshot?): List<Candidate> {
        val candidates: MutableList<Candidate> = mutableListOf()
        val documents: List<DocumentChange> = snapshot!!.documentChanges
        for (document in documents) {
            val candidate = document.document.toObject<Candidate>()
            candidates.add(candidate)
        }
        return candidates
    }

    private fun convertSnapshot(document: DocumentSnapshot?): Candidate? =
        document?.toObject<Candidate>()
}