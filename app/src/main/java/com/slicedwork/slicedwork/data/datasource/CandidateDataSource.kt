package com.slicedwork.slicedwork.data.datasource

import com.slicedwork.slicedwork.domain.model.Candidate

interface CandidateDataSource {

    suspend fun registerCandidate(candidate: Candidate)
}