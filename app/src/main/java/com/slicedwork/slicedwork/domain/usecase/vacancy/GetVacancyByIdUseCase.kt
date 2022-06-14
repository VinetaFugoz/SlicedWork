package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.slicedwork.slicedwork.domain.model.Vacancy

interface GetVacancyByIdUseCase {
    suspend operator fun invoke(vacancyId: String, vacancyCallback: (Vacancy) -> Unit)
}