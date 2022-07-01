package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.slicedwork.slicedwork.data.repository.VacancyRepository
import com.slicedwork.slicedwork.domain.model.Vacancy
import javax.inject.Inject

class GetVacanciesUseCaseImpl @Inject constructor(private val vacancyRepository: VacancyRepository) :
    GetVacanciesUseCase {
    override suspend fun invoke(
        isInHome: Boolean,
        status: Int?,
        userId: String,
        vacancyCallBack: (List<Vacancy>) -> Unit
    ) {
        vacancyRepository.getVacancies(isInHome = isInHome, userId = userId) { vacancies ->
            vacancyCallBack(vacancies)
        }
    }
}