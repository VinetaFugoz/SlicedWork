package com.slicedwork.slicedwork.util.enumerator

import com.slicedwork.slicedwork.R

enum class OccupationAreaEnum {
    PAINTING {
        override fun getImage(): Int = R.drawable.ic_painting
    },
    CLEANING {
        override fun getImage(): Int = R.drawable.ic_cleaning
    },
    GARDENING {
        override fun getImage(): Int = R.drawable.ic_gardening
    },
    CONSTRUCTION {
        override fun getImage(): Int = R.drawable.ic_construction
    },
    ELECTRIC {
        override fun getImage(): Int = R.drawable.ic_electric
    },
    PLUMBING {
        override fun getImage(): Int = R.drawable.ic_plumbing
    };

    abstract fun getImage(): Int
}