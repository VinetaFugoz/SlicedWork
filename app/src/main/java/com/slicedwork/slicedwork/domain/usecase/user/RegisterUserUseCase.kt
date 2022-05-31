package com.slicedwork.slicedwork.domain.usecase.user

import com.slicedwork.slicedwork.domain.model.User

interface RegisterUserUseCase {
    suspend operator fun invoke(user: User, userCallBack: (Boolean) -> Unit)
}
