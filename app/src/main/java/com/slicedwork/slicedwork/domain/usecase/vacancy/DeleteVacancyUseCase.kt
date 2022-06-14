package com.slicedwork.slicedwork.domain.usecase.vacancy

interface DeleteVacancyUseCase {
    suspend operator fun invoke(vacancyId: String, vacancyCallback: (Boolean) -> Unit)
}