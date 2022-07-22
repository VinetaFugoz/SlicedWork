package com.slicedwork.slicedwork.data.datasource

import com.slicedwork.slicedwork.domain.model.Candidate

interface CandidateDataSource {
    suspend fun registerCandidate(candidate: Candidate, candidateCallback: (Boolean) -> Unit)

    suspend fun getCandidatesByIdCandidate(field: String, value: String, candidateCallback: (List<Candidate>) -> Unit)

    suspend fun getCandidateByUserAndVacancy(field: String, value: Any, vacancyId: String, candidateCallback: (Candidate?) -> Unit)

    suspend fun updateCandidate(document: String, field: String, value: Any, candidateCallback: (Boolean) -> Unit)
}