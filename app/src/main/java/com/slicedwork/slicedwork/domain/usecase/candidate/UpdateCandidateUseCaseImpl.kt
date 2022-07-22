package com.slicedwork.slicedwork.domain.usecase.candidate

import com.slicedwork.slicedwork.data.repository.CandidateRepository
import javax.inject.Inject

class UpdateCandidateUseCaseImpl @Inject constructor(private val candidateRepository: CandidateRepository): UpdateCandidateUseCase {
    override suspend fun invoke(document: String, field: String, value: Any, candidateCallback: (Boolean) -> Unit) {
        candidateRepository.updateCandidate(document, field, value) { isUpdated -> candidateCallback(isUpdated) }
    }
}