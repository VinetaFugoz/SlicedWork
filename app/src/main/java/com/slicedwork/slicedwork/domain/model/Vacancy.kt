package com.slicedwork.slicedwork.domain.model

import java.io.Serializable

data class Vacancy(
    val id: String = "",
    val userId: String = "",
    var task: String = "",
    var description: String = "",
    var occupationArea: String = "",
    var price: String = "",
    var picture: String = "",
    var country: String = "",
    var state: String = "",
    var city: String = "",
    var neighborhood: String = "",
    var postalCode: String = "",
    var street: String = "",
    var number: String = ""
): Serializable
