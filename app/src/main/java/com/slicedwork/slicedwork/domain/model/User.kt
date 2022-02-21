package com.slicedwork.slicedwork.domain.model

import android.net.Uri
import java.io.Serializable

class User(
    val id: String = "",
    var uriImage: String? = "",
    val status: String = "",
    val isOnline: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val gender: String = "",
    val birthYear: String = "",
    val areaCode: String = "",
    val phoneNumber: String = "",
    val rating: String = "",
    val creationDate: String = "",
    val updateDate: String = "",
    val deletionDate: String = "",
) : Serializable