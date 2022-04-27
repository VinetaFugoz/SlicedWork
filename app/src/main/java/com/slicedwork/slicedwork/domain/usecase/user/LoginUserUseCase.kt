package com.slicedwork.slicedwork.domain.usecase.user

interface LoginUserUseCase {
    suspend operator fun invoke(email: String, password: String)
}