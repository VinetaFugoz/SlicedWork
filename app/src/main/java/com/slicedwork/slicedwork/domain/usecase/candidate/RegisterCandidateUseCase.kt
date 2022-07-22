package com.slicedwork.slicedwork.domain.usecase.candidate

import com.slicedwork.slicedwork.domain.model.Candidate

interface RegisterCandidateUseCase {
    suspend operator fun invoke(candidate: Candidate, candidateCallback: (Boolean) -> Unit)
}