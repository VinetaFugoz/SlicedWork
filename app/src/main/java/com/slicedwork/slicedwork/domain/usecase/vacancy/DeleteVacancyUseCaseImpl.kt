package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.slicedwork.slicedwork.data.repository.VacancyRepository
import javax.inject.Inject

class DeleteVacancyUseCaseImpl @Inject constructor(private val vacancyRepository: VacancyRepository): DeleteVacancyUseCase {
    override suspend fun invoke(vacancyId: String, vacancyCallback: (Boolean) -> Unit) {
        vacancyRepository.deleteVacancy(vacancyId) { deleted ->
            vacancyCallback(deleted)
        }
    }
}