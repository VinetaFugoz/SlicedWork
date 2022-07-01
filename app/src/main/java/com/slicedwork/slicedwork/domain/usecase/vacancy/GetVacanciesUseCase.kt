package com.slicedwork.slicedwork.domain.usecase.vacancy

import com.google.firebase.auth.FirebaseAuth
import com.slicedwork.slicedwork.domain.model.Vacancy

interface GetVacanciesUseCase {
    suspend operator fun invoke(
        isInHome: Boolean = false,
        status: Int? = null,
        userId: String = FirebaseAuth.getInstance().currentUser!!.uid,
        vacancyCallBack: (List<Vacancy>) -> Unit
    )
}