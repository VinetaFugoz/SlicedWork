package com.slicedwork.slicedwork.domain.model

import java.io.Serializable

data class Candidate(
    val id: String = "",
    val userId: String = "",
    val vacancyId: String = "",
    val status: Int = 1
): Serializable
