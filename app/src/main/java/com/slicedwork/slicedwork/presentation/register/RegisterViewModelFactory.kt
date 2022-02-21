package com.slicedwork.slicedwork.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.slicedwork.slicedwork.data.repository.UserRepository
import java.lang.IllegalArgumentException

class RegisterViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java))
            return RegisterViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}