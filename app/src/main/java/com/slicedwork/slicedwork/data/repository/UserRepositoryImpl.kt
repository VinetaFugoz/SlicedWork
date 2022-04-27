package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.data.datasource.UserDataSource
import com.slicedwork.slicedwork.domain.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDataSource: UserDataSource): UserRepository {
    override suspend fun registerUser(user: User) = userDataSource.registerUser(user)
    override suspend fun loginUser(email: String, password: String) = userDataSource.loginUser(email, password)
}