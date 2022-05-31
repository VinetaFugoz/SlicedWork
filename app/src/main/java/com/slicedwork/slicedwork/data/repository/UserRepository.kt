package com.slicedwork.slicedwork.data.repository

import com.slicedwork.slicedwork.domain.model.User

interface UserRepository {
    suspend fun registerUser(user: User, userCallBack: (Boolean) -> Unit)

    suspend fun loginUser(email: String, password: String, userCallBack: (Boolean) -> Unit)

    suspend fun getUser(userId: String, userCallback: (User) -> Unit)
}