package com.slicedwork.slicedwork.domain.usecase.user

import com.slicedwork.slicedwork.data.repository.UserRepository
import com.slicedwork.slicedwork.domain.model.User
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository): GetUserUseCase {
    override suspend fun invoke(userId: String, userCallBack: (User) -> Unit) {
        userRepository.getUser(userId) { user ->
            userCallBack(user)
        }
    }
}