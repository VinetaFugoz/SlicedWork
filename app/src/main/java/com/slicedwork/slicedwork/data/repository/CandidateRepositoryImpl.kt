package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.data.datasource.CandidateDataSource
import com.slicedwork.slicedwork.domain.model.Candidate
import javax.inject.Inject

class CandidateRepositoryImpl @Inject constructor(private val candidateDataSource: CandidateDataSource): CandidateRepository {

    override suspend fun registerCandidate(
        candidate: Candidate,
        candidateCallback: (Boolean) -> Unit
    ) {
        candidateDataSource.registerCandidate(candidate) { isRegistered ->
            candidateCallback(isRegistered)
        }
    }

    override suspend fun getCandidatesByUserId(
        field: String,
        value: String,
        candidateCallback: (List<Candidate>) -> Unit
    ) {
        candidateDataSource.getCandidatesByIdCandidate(field, value) { candidates ->
            candidateCallback(candidates)
        }
    }

    override suspend fun getCandidateByFieldAndVacancy(field: String, value: Any, vacancyId: String, candidateCallback: (Candidate?) -> Unit) {
        candidateDataSource.getCandidateByUserAndVacancy(field, value, vacancyId) { candidate ->
            candidateCallback(candidate)
        }
    }

    override suspend fun updateCandidate(
        document: String,
        field: String,
        value: Any,
        candidateCallback: (Boolean) -> Unit
    ) {
        candidateDataSource.updateCandidate(document, field, value) { isUpdated ->
            candidateCallback(isUpdated)
        }
    }
}