package com.slicedwork.slicedwork.presentation.register

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slicedwork.slicedwork.data.repository.UserRepository
import com.slicedwork.slicedwork.domain.model.Response
import com.slicedwork.slicedwork.domain.model.User
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    private val _isUserRegisterLiveData = MutableLiveData<Response<Void?>>(Response.Success(null))
    val isUserRegisterLiveData: LiveData<Response<Void?>> get() = _isUserRegisterLiveData

    fun registerUser(user: User, uriImage: Uri?) {
        viewModelScope.launch {
            repository.registerUser(user, uriImage)
        }
    }
}