package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.data.datasource.VacancyDataSource
import com.slicedwork.slicedwork.domain.model.Vacancy
import javax.inject.Inject

class VacancyRepositoryImpl @Inject constructor(private val vacancyDataSource: VacancyDataSource) :
    VacancyRepository {
    override suspend fun registerVacancy(vacancy: Vacancy) =
        vacancyDataSource.registerVacancy(vacancy)

    override suspend fun getVacancies(
        isInHome: Boolean,
        status: Int?,
        vacancyCallback: (List<Vacancy>) -> Unit
    ) {
        vacancyDataSource.getVacancies(isInHome = isInHome) { vacancies ->
            vacancyCallback(vacancies)
        }
    }
}