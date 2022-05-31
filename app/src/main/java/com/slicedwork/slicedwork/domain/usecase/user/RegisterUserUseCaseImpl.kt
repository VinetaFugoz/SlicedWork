package com.slicedwork.slicedwork.domain.usecase.user

import com.slicedwork.slicedwork.data.repository.UserRepository
import com.slicedwork.slicedwork.domain.model.User
import javax.inject.Inject

class RegisterUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    RegisterUserUseCase {
    override suspend fun invoke(user: User, userCallBack: (Boolean) -> Unit) =
        userRepository.registerUser(user) { registered ->
            userCallBack(registered)
        }
}