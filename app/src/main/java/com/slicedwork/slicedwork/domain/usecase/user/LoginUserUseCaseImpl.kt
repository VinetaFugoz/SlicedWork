package com.slicedwork.slicedwork.domain.usecase.user

import com.slicedwork.slicedwork.data.repository.UserRepository
import javax.inject.Inject

class LoginUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    LoginUserUseCase {

    override suspend fun invoke(email: String, password: String) =
        userRepository.loginUser(email, password)
}