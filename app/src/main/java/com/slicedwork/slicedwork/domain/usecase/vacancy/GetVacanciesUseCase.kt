package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.slicedwork.slicedwork.domain.model.Vacancy

interface GetVacanciesUseCase {
    suspend operator fun invoke(isInHome: Boolean = false, status: Int? = null, vacancyCallBack: (List<Vacancy>) -> Unit)
}