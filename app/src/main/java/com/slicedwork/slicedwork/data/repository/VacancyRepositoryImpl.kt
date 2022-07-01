package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.data.datasource.VacancyDataSource
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.util.enumerator.FieldEnum
import javax.inject.Inject

class VacancyRepositoryImpl @Inject constructor(private val vacancyDataSource: VacancyDataSource) :
    VacancyRepository {
    override suspend fun registerVacancy(vacancy: Vacancy, vacancyCallback: (Boolean) -> Unit) =
        vacancyDataSource.registerVacancy(vacancy) { isRegistered ->
            vacancyCallback(isRegistered)
        }

    override suspend fun getVacancies(
        isInHome: Boolean,
        status: Int?,
        userId: String,
        vacancyCallback: (List<Vacancy>) -> Unit
    ) {
        vacancyDataSource.getVacancies(isInHome = isInHome, userId = userId) { vacancies ->
            vacancyCallback(vacancies)
        }
    }

    override suspend fun getVacancyById(vacancyId: String, vacancyCallback: (Vacancy) -> Unit) {
        vacancyDataSource.getVacancyById(vacancyId) { vacancy ->
            vacancyCallback(vacancy)
        }
    }

    override suspend fun updateVacancy(
        isMenu: Boolean,
        document: String,
        field: FieldEnum,
        value: String,
        vacancyCallback: (Boolean) -> Unit
    ) {
        vacancyDataSource.updateVacancy(isMenu, document, field, value) { updated ->
            vacancyCallback(updated)
        }
    }

    override suspend fun deleteVacancy(vacancyId: String, vacancyCallback: (Boolean) -> Unit) {
        vacancyDataSource.deleteVacancy(vacancyId) { deleted ->
            vacancyCallback(deleted)
        }
    }
}