package com.slicedwork.slicedwork.domain.usecase.candidate

import com.slicedwork.slicedwork.data.repository.CandidateRepository
import com.slicedwork.slicedwork.domain.model.Candidate
import javax.inject.Inject

class RegisterCandidateUseCaseImpl @Inject constructor(private val candidateRepository: CandidateRepository) :
    RegisterCandidateUseCase {
    override suspend fun invoke(candidate: Candidate, candidateCallback: (Boolean) -> Unit) {
        candidateRepository.registerCandidate(candidate) { isRegistered ->
            candidateCallback(isRegistered)
        }
    }
}