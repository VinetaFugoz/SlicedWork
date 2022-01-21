package com.slicedwork.slicedwork.domain.builder

import com.slicedwork.slicedwork.domain.model.User

object UserBuilder {
    private var id: String = ""
    private var uriImage: String = ""
    private var status: String = ""
    private var isOnline: Boolean = false
    private var name: String = ""
    private var username: String = ""
    private var gender: String = ""
    private var birthYear: String = ""
    private var areaCode: String = ""
    private var phoneNumber: String = ""
    private var rating: String = ""
    private var creationDate: String = ""
    private var updateDate: String = ""
    private var deletionDate: String = ""

    fun id(id: String): UserBuilder {
        this.id = id
        return this
    }

    fun uriImage(uriImage: String): UserBuilder {
        this.uriImage = uriImage
        return this
    }

    fun status(status: String): UserBuilder {
        this.status = status
        return this
    }

    fun isOnline(isOnline: Boolean): UserBuilder {
        this.isOnline = isOnline
        return this
    }

    fun name(name: String): UserBuilder {
        this.name = name
        return this
    }

    fun username(username: String): UserBuilder {
        this.username = username
        return this
    }

    fun gender(gender: String): UserBuilder {
        this.gender = gender
        return this
    }

    fun birthYear(birthYear: String): UserBuilder {
        this.birthYear = birthYear
        return this
    }

    fun areaCode(areaCode: String): UserBuilder {
        this.areaCode = areaCode
        return this
    }

    fun phoneNumber(phoneNumber: String): UserBuilder {
        this.phoneNumber = phoneNumber
        return this
    }

    fun rating(rating: String): UserBuilder {
        this.rating = rating
        return this
    }

    fun creationDate(creationDate: String): UserBuilder {
        this.creationDate = creationDate
        return this
    }

    fun updateDate(updateDate: String): UserBuilder {
        this.updateDate = updateDate
        return this
    }

    fun deletionDate(deletionDate: String): UserBuilder {
        this.deletionDate = deletionDate
        return this
    }

    fun build(): User {
        return User(
            id,
            uriImage,
            status,
            isOnline,
            name,
            username,
            gender,
            birthYear,
            areaCode,
            phoneNumber,
            rating,
            creationDate,
            updateDate,
            deletionDate
        )
    }

}