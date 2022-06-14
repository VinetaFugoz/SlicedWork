package com.slicedwork.slicedwork.util.enumerator

import android.content.Context
import com.slicedwork.slicedwork.R

enum class FieldEnum {
    STATUS {
        override fun getText(): String = "status"
        override fun getText(context: Context): String = context.resources.getString(R.string.vacancy_details_status)
    };

    abstract fun getText(): String
    abstract fun getText(context: Context): String
}