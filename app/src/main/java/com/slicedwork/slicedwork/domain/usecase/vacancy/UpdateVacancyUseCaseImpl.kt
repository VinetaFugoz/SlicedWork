package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.slicedwork.slicedwork.data.repository.VacancyRepository
import com.slicedwork.slicedwork.util.enumerator.FieldEnum
import javax.inject.Inject

class UpdateVacancyUseCaseImpl @Inject constructor(private val vacancyRepository: VacancyRepository): UpdateVacancyUseCase {
    override suspend fun invoke(isMenu: Boolean, document: String, field: FieldEnum, value: String, vacancyCallback: (Boolean) -> Unit) {
        vacancyRepository.updateVacancy(isMenu, document, field, value) { updated ->
            vacancyCallback(updated)
        }
    }
}