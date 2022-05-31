package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.data.datasource.UserDataSource
import com.slicedwork.slicedwork.domain.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDataSource: UserDataSource) :
    UserRepository {
    override suspend fun registerUser(user: User, userCallBack: (Boolean) -> Unit) =
        userDataSource.registerUser(user) { registered ->
            userCallBack(registered)
        }

    override suspend fun loginUser(email: String, password: String, userCallBack: (Boolean) -> Unit) =
        userDataSource.loginUser(email, password) { logged ->
            userCallBack(logged)
        }

    override suspend fun getUser(userId: String, userCallback: (User) -> Unit) {
        userDataSource.getUser(userId) { user ->
            userCallback(user)
        }
    }
}