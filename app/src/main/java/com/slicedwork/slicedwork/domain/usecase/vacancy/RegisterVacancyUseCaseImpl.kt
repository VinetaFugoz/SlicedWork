package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.slicedwork.slicedwork.data.repository.VacancyRepository
import com.slicedwork.slicedwork.domain.model.Vacancy
import javax.inject.Inject

class RegisterVacancyUseCaseImpl @Inject constructor(private val vacancyRepository: VacancyRepository): RegisterVacancyUseCase {
    override suspend fun invoke(vacancy: Vacancy) = vacancyRepository.registerVacancy(vacancy)
}