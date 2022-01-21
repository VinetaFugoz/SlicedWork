package com.slicedwork.slicedwork.domain.model

import java.io.Serializable

class User(
    val id: String,
    val uriImage: String,
    val status: String,
    val isOnline: Boolean,
    val name: String,
    val username: String,
    val gender: String,
    val birthYear: String,
    val areaCode: String,
    val phoneNumber: String,
    val rating: String,
    val creationDate: String,
    val updateDate: String,
    val deletionDate: String
) : Serializable