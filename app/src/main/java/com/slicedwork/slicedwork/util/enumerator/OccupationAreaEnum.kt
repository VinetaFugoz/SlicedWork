package com.slicedwork.slicedwork.util.enumerator

import android.content.Context
import com.slicedwork.slicedwork.R

enum class OccupationAreaEnum {
    PAINTING {
        override fun getImage(): Int = R.drawable.ic_painting
        override fun getText(index: Int, context: Context): String =
            context.resources.getString(R.string.painting)
    },
    CLEANING {
        override fun getImage(): Int = R.drawable.ic_cleaning
        override fun getText(index: Int, context: Context): String =
            context.resources.getString(R.string.cleaning)
    },
    GARDENING {
        override fun getImage(): Int = R.drawable.ic_gardening
        override fun getText(index: Int, context: Context): String =
            context.resources.getString(R.string.gardening)
    },
    CONSTRUCTION {
        override fun getImage(): Int = R.drawable.ic_construction
        override fun getText(index: Int, context: Context): String =
            context.resources.getString(R.string.construction)
    },
    ELECTRIC {
        override fun getImage(): Int = R.drawable.ic_electric
        override fun getText(index: Int, context: Context): String =
            context.resources.getString(R.string.electric)
    },
    PLUMBING {
        override fun getImage(): Int = R.drawable.ic_plumbing
        override fun getText(index: Int, context: Context): String =
            context.resources.getString(R.string.plumbing)
    };

    abstract fun getImage(): Int
    abstract fun getText(index: Int, context: Context): String
}