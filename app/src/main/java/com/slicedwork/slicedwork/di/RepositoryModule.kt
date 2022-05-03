package com.slicedwork.slicedwork.di

import com.slicedwork.slicedwork.data.repository.UserRepository
import com.slicedwork.slicedwork.data.repository.UserRepositoryImpl
import com.slicedwork.slicedwork.data.repository.VacancyRepository
import com.slicedwork.slicedwork.data.repository.VacancyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    fun bindVacancyRepository(vacancyRepository: VacancyRepositoryImpl): VacancyRepository
}