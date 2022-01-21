package com.slicedwork.slicedwork.domain.factory

import android.net.Uri
import com.slicedwork.slicedwork.databinding.FragmentRegisterBinding
import com.slicedwork.slicedwork.domain.builder.UserBuilder
import java.util.*

object UserFactory {
    fun createUserDefault() =
        UserBuilder
            .id("")
            .uriImage("")
            .status("")
            .isOnline(false)
            .name("")
            .username("")
            .gender("")
            .birthYear("")
            .areaCode("")
            .phoneNumber("")
            .rating("")
            .creationDate("")
            .updateDate("")
            .deletionDate("")
            .build()

    fun createUserByBinding(binding: FragmentRegisterBinding, tempImageUri: Uri?) =
        UserBuilder
            .id(UUID.randomUUID().toString())
            .uriImage(tempImageUri.toString())
            .status("")
            .isOnline(false)
            .name(
                binding.etFirstName.text.toString()
                        + "" + binding.etLastName.text.toString()
            )
            .username(binding.etUser.text.toString())
            .gender(binding.avtvGender.text.toString())
            .birthYear(binding.actvBirthYear.text.toString())
            .areaCode(binding.etAreaCode.text.toString())
            .phoneNumber(binding.etPhoneNumber.text.toString())
            .rating("")
            .creationDate(System.currentTimeMillis().toString())
            .updateDate(System.currentTimeMillis().toString())
            .deletionDate("")
            .build()
}