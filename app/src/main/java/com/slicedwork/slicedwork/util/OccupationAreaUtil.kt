package com.slicedwork.slicedwork.util

import android.content.Context
import android.graphics.drawable.Drawable
import com.slicedwork.slicedwork.util.enumerator.OccupationAreaEnum.*

object OccupationAreaUtil {

    val occupationAreaList: List<Int> = listOf(
        PAINTING.ordinal,
        CLEANING.ordinal,
        GARDENING.ordinal,
        CONSTRUCTION.ordinal,
        ELECTRIC.ordinal,
        PLUMBING.ordinal
    )

    fun getOccupationAreaDrawable(occupationArea: Int, context: Context): Drawable? =
        when (occupationArea) {
            PAINTING.ordinal -> PAINTING.getImage(context)
            CLEANING.ordinal -> CLEANING.getImage(context)
            GARDENING.ordinal -> GARDENING.getImage(context)
            CONSTRUCTION.ordinal -> CONSTRUCTION.getImage(context)
            ELECTRIC.ordinal -> ELECTRIC.getImage(context)
            else -> PLUMBING.getImage(context)
        }

    fun getOccupationAreaString(occupationArea: Int, context: Context): String =
        when (occupationArea) {
            PAINTING.ordinal -> PAINTING.getText(context)
            CLEANING.ordinal -> CLEANING.getText(context)
            GARDENING.ordinal -> GARDENING.getText(context)
            CONSTRUCTION.ordinal -> CONSTRUCTION.getText(context)
            ELECTRIC.ordinal -> ELECTRIC.getText(context)
            else -> PAINTING.getText(context)
        }

}