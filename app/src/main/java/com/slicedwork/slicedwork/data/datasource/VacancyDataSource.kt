package com.slicedwork.slicedwork.data.datasource

import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.util.enumerator.FieldEnum

interface VacancyDataSource {
    suspend fun registerVacancy(vacancy: Vacancy, vacancyCallback: (Boolean) -> Unit)

    suspend fun getVacancies(isInHome: Boolean = false, status: Int? = null, vacancyCallback: (List<Vacancy>) -> Unit)

    suspend fun getVacancyById(vacancyId: String, vacancyCallback: (Vacancy) -> Unit)

    suspend fun updateVacancy(isMenu: Boolean, document: String, field: FieldEnum, value: String, vacancyCallback: (Boolean) -> Unit)

    suspend fun deleteVacancy(vacancyId: String, vacancyCallback: (Boolean) -> Unit)
}