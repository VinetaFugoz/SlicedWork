package com.slicedwork.slicedwork.util.enumerator

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.slicedwork.slicedwork.R

enum class OccupationAreaEnum {
    PAINTING {
        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_painting)

        override fun getText(context: Context): String =
            context.resources.getString(R.string.painting)
    },
    CLEANING {
        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_cleaning)

        override fun getText(context: Context): String =
            context.resources.getString(R.string.cleaning)
    },
    GARDENING {
        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_gardening)

        override fun getText(context: Context): String =
            context.resources.getString(R.string.gardening)
    },
    CONSTRUCTION {
        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_construction)

        override fun getText(context: Context): String =
            context.resources.getString(R.string.construction)
    },
    ELECTRIC {
        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_electric)

        override fun getText(context: Context): String =
            context.resources.getString(R.string.electric)
    },
    PLUMBING {
        override fun getImage(context: Context): Drawable? =
            ContextCompat.getDrawable(context, R.drawable.ic_plumbing)

        override fun getText(context: Context): String =
            context.resources.getString(R.string.plumbing)
    };

    abstract fun getImage(context: Context): Drawable?
    abstract fun getText(context: Context): String
}