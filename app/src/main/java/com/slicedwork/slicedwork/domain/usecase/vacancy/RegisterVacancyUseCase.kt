package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.slicedwork.slicedwork.domain.model.Vacancy

interface RegisterVacancyUseCase {
    suspend operator fun invoke(vacancy: Vacancy, vacancyCallback: (Boolean) -> Unit)
}