package com.slicedwork.slicedwork.domain.model

data class Rating(
    var id: String = "",
    var fromUserId: String = "",
    var toUserId: String = "",
    var rating: Double = 0.0
)
