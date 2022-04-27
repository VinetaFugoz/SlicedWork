package com.slicedwork.slicedwork.di

import com.slicedwork.slicedwork.domain.usecase.user.LoginUserUseCase
import com.slicedwork.slicedwork.domain.usecase.user.LoginUserUseCaseImpl
import com.slicedwork.slicedwork.domain.usecase.user.RegisterUserUseCase
import com.slicedwork.slicedwork.domain.usecase.user.RegisterUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindRegisterUserUseCase(registerUserUseCase: RegisterUserUseCaseImpl): RegisterUserUseCase

    @Binds
    fun bindLoginUserUseCase(loginUserUseCase: LoginUserUseCaseImpl): LoginUserUseCase
}