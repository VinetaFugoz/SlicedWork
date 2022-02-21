package com.slicedwork.slicedwork.util.temporary

import android.content.Context
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import java.util.*

fun getVacancies(): List<Vacancy> {
    return listOf(
        Vacancy(
            id = "1",
            urlImage = R.drawable.rakanandxayahpenguin,
            status = "aaa",
            creationDate = "aaa",
            updateDate = "aaa",
            deletionDate = "aaa",
            price = "aaa",
            occupationArea = "aaa",
            task = "aaa",
            description = "aaa",
            postalCode = "aaa",
            state = "aaa",
            city = "aaa",
            district = "aaa",
            publicPlace = "aaa",
            number = "aaa",
            userId = "1"
        ),
        Vacancy(
            id = "2",
            urlImage = R.drawable.rakanandxayahpenguin,
            status = "aaa",
            creationDate = "aaa",
            updateDate = "aaa",
            deletionDate = "aaa",
            price = "aaa",
            occupationArea = "aaa",
            task = "aaa",
            description = "aaa",
            postalCode = "aaa",
            state = "aaa",
            city = "aaa",
            district = "aaa",
            publicPlace = "aaa",
            number = "aaa",
            userId = "2"
        ),
        Vacancy(
            id = "3",
            urlImage = R.drawable.rakanandxayahpenguin,
            status = "aaa",
            creationDate = "aaa",
            updateDate = "aaa",
            deletionDate = "aaa",
            price = "aaa",
            occupationArea = "aaa",
            task = "aaa",
            description = "aaa",
            postalCode = "aaa",
            state = "aaa",
            city = "aaa",
            district = "aaa",
            publicPlace = "aaa",
            number = "aaa",
            userId = "3"
        ),
        Vacancy(
            id = "4",
            urlImage = R.drawable.rakanandxayahpenguin,
            status = "aaa",
            creationDate = "aaa",
            updateDate = "aaa",
            deletionDate = "aaa",
            price = "aaa",
            occupationArea = "aaa",
            task = "aaa",
            description = "aaa",
            postalCode = "aaa",
            state = "aaa",
            city = "aaa",
            district = "aaa",
            publicPlace = "aaa",
            number = "aaa",
            userId = "4"
        ),
        Vacancy(
            id = "5",
            urlImage = R.drawable.rakanandxayahpenguin,
            status = "aaa",
            creationDate = "aaa",
            updateDate = "aaa",
            deletionDate = "aaa",
            price = "aaa",
            occupationArea = "aaa",
            task = "aaa",
            description = "aaa",
            postalCode = "aaa",
            state = "aaa",
            city = "aaa",
            district = "aaa",
            publicPlace = "aaa",
            number = "aaa",
            userId = "1"
        ),
        Vacancy(
            id = "6",
            urlImage = R.drawable.rakanandxayahpenguin,
            status = "aaa",
            creationDate = "aaa",
            updateDate = "aaa",
            deletionDate = "aaa",
            price = "aaa",
            occupationArea = "aaa",
            task = "aaa",
            description = "aaa",
            postalCode = "aaa",
            state = "aaa",
            city = "aaa",
            district = "aaa",
            publicPlace = "aaa",
            number = "aaa",
            userId = "2"
        ),
    )
}

fun getUserById(users: List<User>, userId: String?): User {
    var user: User = users[0]
    for (currentUser: User in users) {
        if (currentUser.id == userId) user = currentUser
    }

    return user
}

fun getYears(): List<String> {
    val yearlist: MutableList<String> = mutableListOf()
    for (i in currentYear() downTo 1960) {
        yearlist.add(i.toString())
    }

    return yearlist
}

fun getGenders(context: Context): List<String> {
    return listOf(
        context.resources.getString(R.string.masculine_gender),
        context.resources.getString(R.string.feminine_gender),
        context.resources.getString(R.string.other_gender)
    )
}

fun currentYear(): Int {
    return Calendar.getInstance().get(Calendar.YEAR)
}
