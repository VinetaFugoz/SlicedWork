package com.slicedwork.slicedwork.domain.usecase.candidate

interface UpdateCandidateUseCase {
    suspend operator fun invoke(
        document: String,
        field: String,
        value: Any,
        candidateCallback: (Boolean) -> Unit
    )
}