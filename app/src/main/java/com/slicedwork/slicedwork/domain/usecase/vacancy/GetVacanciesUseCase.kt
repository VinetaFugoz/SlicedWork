package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.slicedwork.slicedwork.domain.model.Vacancy

interface GetVacanciesUseCase {
    suspend operator fun invoke(vacancyCallBack: (List<Vacancy>) -> Unit)
}