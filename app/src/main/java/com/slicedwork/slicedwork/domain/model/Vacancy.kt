package com.slicedwork.slicedwork.domain.model

import java.io.Serializable

data class Vacancy(
    val id: String,
    val urlImage: Int?,
    val status: String,
    val creationDate: String,
    val updateDate: String,
    val deletionDate: String,
    val price: String?,
    val occupationArea: String,
    val task: String,
    val description: String?,
    val postalCode: String?,
    val state: String?,
    val city: String?,
    val district: String?,
    val publicPlace: String?,
    val number: String?,
    val userId: String
) : Serializable