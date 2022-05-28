package com.slicedwork.slicedwork.util.validator

import java.util.regex.Pattern

class UserValidator: Validator() {

    fun validateFirstName(firstName: String): Boolean {
        if (isEmpty(firstName)) return false

        return true
    }

    fun validateLastName(lastName: String): Boolean {
        if (isEmpty(lastName)) return false

        return true
    }


    private fun isEmailValidRegex(email: String): Boolean {
        val emailRegex = Pattern
            .compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE
            )
        return (emailRegex.matcher(email).find())
    }

    fun validatePhoneNumber(phoneNumber: String): Boolean {
        if (phoneNumber.length != 11) return false

        return true
    }

    fun validateNickname(nickname: String): Boolean {
        if (isEmpty(nickname)) return false

        return true
    }

    fun validateEmail(email: String): Boolean {
        if (isEmpty(email)) return false

        return isEmailValidRegex(email)
    }

    fun validatePassword(password: String): Boolean {
        if (isEmpty(password) || password.length < 6) return false

        return true
    }
}

