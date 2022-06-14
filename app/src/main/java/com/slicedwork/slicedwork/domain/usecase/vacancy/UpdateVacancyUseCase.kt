package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.slicedwork.slicedwork.util.enumerator.FieldEnum

interface UpdateVacancyUseCase {
   suspend operator fun invoke(isMenu: Boolean, document: String, field: FieldEnum, value: String, vacancyCallback: (Boolean) -> Unit)
}