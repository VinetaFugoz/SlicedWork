package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.domain.model.Candidate

interface CandidateRepository {
    suspend fun registerCandidate(candidate: Candidate)
}