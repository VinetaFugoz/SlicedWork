package com.slicedwork.slicedwork.domain.usecase.candidate

import com.slicedwork.slicedwork.data.repository.CandidateRepository
import com.slicedwork.slicedwork.domain.model.Candidate
import javax.inject.Inject

class GetCandidatesByIdUseCaseImpl @Inject constructor(private val candidateRepository: CandidateRepository) :
    GetCandidatesByIdUseCase {
    override suspend fun invoke(field:String, value: String, candidateCallback: (List<Candidate>) -> Unit) {
        candidateRepository.getCandidatesByUserId(field, value) { candidates ->
            candidateCallback(candidates)
        }
    }
}