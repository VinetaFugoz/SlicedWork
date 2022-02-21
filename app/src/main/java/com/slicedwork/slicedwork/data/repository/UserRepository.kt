package com.slicedwork.slicedwork.data.repository

import android.net.Uri
import com.slicedwork.slicedwork.domain.model.User

interface UserRepository {
    fun loginUser(user: User)
    fun registerUser(user: User, uriImage: Uri?)
}