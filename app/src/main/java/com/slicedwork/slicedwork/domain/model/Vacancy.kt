package com.slicedwork.slicedwork.domain.model

import java.io.Serializable

data class Vacancy(
    val id: String = "",
    val uuid: String = "",
    var task: String = "",
    var description: String = "",
    var occupationArea: String = "",
    var price: String = "",
    var picture: String = "",
    var number: String = "",
    var street: String = "",
    var neighborhood: String = "",
    var city: String = "",
    var country: String = "",
    var postalCode: String = ""
): Serializable
