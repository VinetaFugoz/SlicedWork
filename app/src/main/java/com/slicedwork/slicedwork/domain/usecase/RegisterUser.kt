package com.slicedwork.slicedwork.domain.usecase

import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.data.repository.UserRepository

class RegisterUser(
    private val userRepository: UserRepository,
) {
    operator fun invoke(user: User) = userRepository.registerUser(user)
}