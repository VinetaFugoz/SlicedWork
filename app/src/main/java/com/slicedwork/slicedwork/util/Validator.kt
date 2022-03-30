package com.slicedwork.slicedwork.util

import java.util.regex.Pattern

object Validator {

    fun validateEmail(email: String): Boolean {
        if (isEmpty(email)) return false

        return isEmailValidRegex(email)
    }

    fun validateFirstName(firstName: String): Boolean {
        if (isEmpty(firstName))  return false

        return true
    }

    fun validateLastName(lastName: String): Boolean {
        if (isEmpty(lastName))  return false

        return true
    }

    private fun isEmpty(field: String) = field.isEmpty()

    private fun isEmailValidRegex(email: String): Boolean {
        val emailRegex = Pattern
            .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE)
        return (emailRegex.matcher(email).find())
    }
}

