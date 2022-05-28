package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.slicedwork.slicedwork.data.repository.VacancyRepository
import com.slicedwork.slicedwork.domain.model.Vacancy
import javax.inject.Inject

class GetVacanciesUseCaseImpl @Inject constructor(private val vacancyRepository: VacancyRepository): GetVacanciesUseCase {
    override suspend fun invoke(vacancyCallBack: (List<Vacancy>) -> Unit) {
        vacancyRepository.getVacancies { vacancies ->
            vacancyCallBack(vacancies)
        }
    }
}