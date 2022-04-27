package com.slicedwork.slicedwork.di

import com.slicedwork.slicedwork.data.repository.UserRepository
import com.slicedwork.slicedwork.data.repository.UserRepositoryImpl
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
}