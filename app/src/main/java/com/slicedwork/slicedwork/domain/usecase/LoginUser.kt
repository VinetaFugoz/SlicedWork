package com.slicedwork.slicedwork.domain.usecase

import com.slicedwork.slicedwork.data.repository.UserRepository

class LoginUser(
    private val userRepository: UserRepository,
    private val email: String,
    private val password: String
) {
    operator fun invoke() = userRepository.loginUser(email, password)
}
