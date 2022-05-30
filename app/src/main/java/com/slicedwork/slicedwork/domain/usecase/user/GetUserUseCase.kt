package com.slicedwork.slicedwork.domain.usecase.user

import com.slicedwork.slicedwork.domain.model.User

interface GetUserUseCase {
    suspend operator fun invoke(userId: String, userCallBack: (User) -> Unit)
}