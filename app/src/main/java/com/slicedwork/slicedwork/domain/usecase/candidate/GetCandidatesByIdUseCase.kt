package com.slicedwork.slicedwork.domain.usecase.candidate

import com.slicedwork.slicedwork.domain.model.Candidate

interface GetCandidatesByIdUseCase {
    suspend operator fun invoke(field: String, value: String, candidateCallback: (List<Candidate>) -> Unit)
}