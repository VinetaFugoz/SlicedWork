package com.slicedwork.slicedwork.util.enumerator

import android.content.Context
import com.slicedwork.slicedwork.R

enum class VacancyStatusEnum {
    ALL {
        override fun getText(context: Context): String =
            context.resources.getString(R.string.announced_vacancies_all)

        override fun getTextFilter(context: Context): String =
            context.resources.getString(R.string.announced_vacancies_all)
    },
    OPENED {
        override fun getText(context: Context): String =
            context.resources.getString(R.string.vacancy_details_opened)

        override fun getTextFilter(context: Context): String =
            context.resources.getString(R.string.announced_vacancies_opened)
    },
    CLOSED {
        override fun getText(context: Context): String =
            context.resources.getString(R.string.vacancy_details_closed)

        override fun getTextFilter(context: Context): String =
            context.resources.getString(R.string.announced_vacancies_closed)
    },
    FINISHED {
        override fun getText(context: Context): String =
            context.resources.getString(R.string.vacancy_details_finished)

        override fun getTextFilter(context: Context): String =
            context.resources.getString(R.string.announced_vacancies_finished)
    };

    abstract fun getText(context: Context): String
    abstract fun getTextFilter(context: Context): String
}