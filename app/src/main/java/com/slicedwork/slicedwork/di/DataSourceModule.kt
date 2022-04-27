package com.slicedwork.slicedwork.di

import com.slicedwork.slicedwork.data.datasource.FirebaseUserDataSource
import com.slicedwork.slicedwork.data.datasource.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindUserDataSource(userDataSource: FirebaseUserDataSource): UserDataSource
}