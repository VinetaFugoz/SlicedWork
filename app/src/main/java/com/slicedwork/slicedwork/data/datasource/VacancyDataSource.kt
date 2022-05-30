package com.slicedwork.slicedwork.data.datasource

import com.slicedwork.slicedwork.domain.model.Vacancy

interface VacancyDataSource {
    suspend fun registerVacancy(vacancy: Vacancy)

    suspend fun getVacancies(vacancyCallback: (List<Vacancy>) -> Unit)
}