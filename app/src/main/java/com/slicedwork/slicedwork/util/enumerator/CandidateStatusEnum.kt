package com.slicedwork.slicedwork.util.enumerator

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.slicedwork.slicedwork.R

enum class CandidateStatusEnum {
    ALL {
        override fun getText(context: Context): String =
            context.resources.getString(R.string.applied_vacancies_all)
    },
    IN_WAITING {
        override fun getText(context: Context): String =
            context.resources.getString(R.string.applied_vacancies_in_waiting)
    },
    APPROVED {
        override fun getText(context: Context): String =
            context.resources.getString(R.string.applied_vacancies_approved)
    },
    DISAPPROVED {
        override fun getText(context: Context): String =
            context.resources.getString(R.string.applied_vacancies_disapproved)
    };

    abstract fun getText(context: Context): String
}