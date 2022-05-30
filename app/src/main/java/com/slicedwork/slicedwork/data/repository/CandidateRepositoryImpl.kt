package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.data.datasource.CandidateDataSource
import com.slicedwork.slicedwork.domain.model.Candidate
import javax.inject.Inject

class CandidateRepositoryImpl @Inject constructor(private val candidateDataSource: CandidateDataSource): CandidateRepository {
    override suspend fun registerCandidate(candidate: Candidate) {
        candidateDataSource.registerCandidate(candidate)
    }
}