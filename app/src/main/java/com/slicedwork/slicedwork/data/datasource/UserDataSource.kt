package com.slicedwork.slicedwork.data.datasource

import com.slicedwork.slicedwork.domain.model.User

interface UserDataSource {
    suspend fun registerUser(user: User, userCallBack: (Boolean) -> Unit)

    suspend fun loginUser(email: String, password: String, userCallBack: (Boolean) -> Unit)

    suspend fun getUser(userId: String, userCallBack: (User) -> Unit)
}
