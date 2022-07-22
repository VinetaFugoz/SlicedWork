package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.domain.model.Candidate

interface CandidateRepository {
    suspend fun registerCandidate(candidate: Candidate, candidateCallback: (Boolean) -> Unit)

    suspend fun getCandidatesByUserId(field: String, value: String, candidateCallback: (List<Candidate>) -> Unit)

    suspend fun getCandidateByFieldAndVacancy(field: String, value: Any, vacancyId: String, candidateCallback: (Candidate?) -> Unit)

    suspend fun updateCandidate(document: String, field: String, value: Any, candidateCallback: (Boolean) -> Unit)
}