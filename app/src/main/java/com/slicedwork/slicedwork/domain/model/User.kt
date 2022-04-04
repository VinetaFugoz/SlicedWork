package com.slicedwork.slicedwork.domain.model

import java.io.Serializable
import java.sql.Timestamp

data class User(
    val uuid: String = "",
    val firstName: String = "",
    val lastName: String = "",
    var birthDate: Long = 0L,
    val gender: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val email: String = ""
) : Serializable
