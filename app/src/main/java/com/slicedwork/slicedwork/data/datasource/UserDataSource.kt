package com.slicedwork.slicedwork.data.datasource

import com.slicedwork.slicedwork.domain.model.User

interface UserDataSource {
    suspend fun registerUser(user: User)

    suspend fun loginUser(email: String, password: String)
}
