package com.slicedwork.slicedwork.domain.model

data class Message(
    val id: String,
    val userFromId: String,
    val userToId: String,
    val text: String,
    val creationDate: String
)