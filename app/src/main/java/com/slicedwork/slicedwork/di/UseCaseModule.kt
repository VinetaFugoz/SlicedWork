package com.slicedwork.slicedwork.di

import com.slicedwork.slicedwork.domain.usecase.user.*
import com.slicedwork.slicedwork.domain.usecase.vacancy.*
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

    @Binds
    fun bindUpdateVacancyUseCase(updateVacancyUseCase: UpdateVacancyUseCaseImpl): UpdateVacancyUseCase

    @Binds
    fun bindGetVacancyByIdUseCase(getVacancyById: GetVacancyByIdUseCaseImpl): GetVacancyByIdUseCase

    @Binds
    fun bindDeleteVacancyUseCase(deleteVacancyUseCase: DeleteVacancyUseCaseImpl): DeleteVacancyUseCase

}