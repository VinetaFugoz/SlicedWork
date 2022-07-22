package com.slicedwork.slicedwork.domain.usecase.candidate

import com.slicedwork.slicedwork.domain.model.Candidate

interface GetCandidateByFieldVacancyUseCase {
    suspend operator fun invoke(field: String, value: Any, vacancyId: String, candidateCallback: (Candidate?) -> Unit)
}