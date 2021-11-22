package com.slicedwork.slicedwork.data.model

import android.os.Parcel
import android.os.Parcelable

data class Vacancy(
    val id: String?,
    val urlImage: Int,
    val status: String?,
    val creationDate: String?,
    val updateDate: String?,
    val deletionDate: String?,
    val price: String?,
    val occupationArea: String?,
    val task: String?,
    val description: String?,
    val postalCode: String?,
    val state: String?,
    val city: String?,
    val district: String?,
    val publicPlace: String?,
    val number: String?,
    val userId: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(urlImage)
        parcel.writeString(status)
        parcel.writeString(creationDate)
        parcel.writeString(updateDate)
        parcel.writeString(deletionDate)
        parcel.writeString(price)
        parcel.writeString(occupationArea)
        parcel.writeString(task)
        parcel.writeString(description)
        parcel.writeString(postalCode)
        parcel.writeString(state)
        parcel.writeString(city)
        parcel.writeString(publicPlace)
        parcel.writeString(number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vacancy> {
        override fun createFromParcel(parcel: Parcel): Vacancy {
            return Vacancy(parcel)
        }

        override fun newArray(size: Int): Array<Vacancy?> {
            return arrayOfNulls(size)
        }
    }
}