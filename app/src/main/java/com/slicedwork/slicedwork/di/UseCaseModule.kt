package com.slicedwork.slicedwork.di

import com.slicedwork.slicedwork.domain.usecase.candidate.RegisterCandidateUseCase
import com.slicedwork.slicedwork.domain.usecase.candidate.RegisterCandidateUseCaseImpl
import com.slicedwork.slicedwork.domain.usecase.user.*
import com.slicedwork.slicedwork.domain.usecase.vacancy.GetVacanciesUseCase
import com.slicedwork.slicedwork.domain.usecase.vacancy.GetVacanciesUseCaseImpl
import com.slicedwork.slicedwork.domain.usecase.vacancy.RegisterVacancyUseCase
import com.slicedwork.slicedwork.domain.usecase.vacancy.RegisterVacancyUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    // User
    @Binds
    fun bindRegisterUserUseCase(registerUserUseCase: RegisterUserUseCaseImpl): RegisterUserUseCase

    @Binds
    fun bindLoginUserUseCase(loginUserUseCase: LoginUserUseCaseImpl): LoginUserUseCase

    @Binds
    fun bindGetUserUseCase(getUserUseCase: GetUserUseCaseImpl): GetUserUseCase

    // Vacancy
    @Binds
    fun bindRegisterVacancyUseCase(registerVacancyUseCase: RegisterVacancyUseCaseImpl): RegisterVacancyUseCase

    @Binds
    fun bindGetVacanciesUseCase(getVacanciesUseCase: GetVacanciesUseCaseImpl): GetVacanciesUseCase

    // Candidate
    @Binds
    fun bindRegisterCandidateUseCase(registerCandidateUseCase: RegisterCandidateUseCaseImpl): RegisterCandidateUseCase
}