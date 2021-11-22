package com.slicedwork.slicedwork.data.model

data class Message(
    val id: String,
    val userFromId: String,
    val userToId: String,
    val text: String,
    val creationDate: String?
)