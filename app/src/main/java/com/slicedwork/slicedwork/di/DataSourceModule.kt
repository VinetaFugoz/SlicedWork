package com.slicedwork.slicedwork.di

import com.slicedwork.slicedwork.data.datasource.*
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
    fun bindUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource

    @Singleton
    @Binds
    fun bindVacancyDataSource(vacancyDataSource: VacancyDataSourceImpl): VacancyDataSource

    @Singleton
    @Binds
    fun bindRatingDataSource(ratingDataSource: RatingDataSourceImpl): RatingDataSource
}