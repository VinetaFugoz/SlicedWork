package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.slicedwork.slicedwork.data.repository.VacancyRepository
import com.slicedwork.slicedwork.domain.model.Vacancy
import javax.inject.Inject

class GetVacancyByIdUseCaseImpl @Inject constructor(private val vacancyRepository: VacancyRepository): GetVacancyByIdUseCase {
    override suspend fun invoke(vacancyId: String, vacancyCallback: (Vacancy) -> Unit) {
        vacancyRepository.getVacancyById(vacancyId) { vacancy ->
            vacancyCallback(vacancy)
        }
    }
}