package com.slicedwork.slicedwork.domain.usecase.candidate

import com.slicedwork.slicedwork.data.repository.CandidateRepository
import com.slicedwork.slicedwork.domain.model.Candidate
import javax.inject.Inject

class GetCandidateByFieldAndVacancyUseCaseImpl @Inject constructor(private val candidateRepository: CandidateRepository): GetCandidateByFieldVacancyUseCase  {
    override suspend fun invoke(field: String, value: Any, vacancyId: String, candidateCallback: (Candidate?) -> Unit) {
        candidateRepository.getCandidateByFieldAndVacancy(field, value, vacancyId) { candidate ->
            candidateCallback(candidate)
        }
    }
}