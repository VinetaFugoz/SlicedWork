package com.slicedwork.slicedwork.domain.factory

import android.net.Uri
import com.slicedwork.slicedwork.databinding.FragmentRegisterBinding
import com.slicedwork.slicedwork.domain.model.User
import java.util.*

object UserFactory {
    fun createUserDefault() =
        User(id = UUID.randomUUID().toString())

    fun createUserByBinding(binding: FragmentRegisterBinding) =
        User(
            id = UUID.randomUUID().toString(),
            uriImage = "",
            firstName = binding.etFirstName.text.toString().trim(),
            lastName = binding.etLastName.text.toString().trim(),
            email = binding.etEmail.text.toString().trim(),
            password = binding.etPassword.text.toString().trim(),
            username = binding.etUser.text.toString().trim(),
            gender = binding.avtvGender.text.toString().trim(),
            birthYear = binding.actvBirthYear.text.toString().trim(),
            areaCode = binding.etAreaCode.text.toString().trim(),
            phoneNumber = binding.etPhoneNumber.text.toString().trim(),
            creationDate = System.currentTimeMillis().toString(),
            updateDate = System.currentTimeMillis().toString()
        )
}