package com.slicedwork.slicedwork.domain.model
import java.io.Serializable

data class Candidate(
    var id: String = "",
    var userId: String = "",
    var vacancyId: String = "",
    var status: Int = 0
): Serializable
