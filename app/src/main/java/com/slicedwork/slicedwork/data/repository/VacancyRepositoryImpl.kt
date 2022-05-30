package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.data.datasource.VacancyDataSource
import com.slicedwork.slicedwork.domain.model.Vacancy
import javax.inject.Inject

class VacancyRepositoryImpl @Inject constructor(private val vacancyDataSource: VacancyDataSource): VacancyRepository {
    override suspend fun registerVacancy(vacancy: Vacancy) = vacancyDataSource.registerVacancy(vacancy)

    override suspend fun getVacancies(vacancyCallback: (List<Vacancy>) -> Unit) {
        vacancyDataSource.getVacancies { vacancies ->
            vacancyCallback(vacancies)
        }
    }
}