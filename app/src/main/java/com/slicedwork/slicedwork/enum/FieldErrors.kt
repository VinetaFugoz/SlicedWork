package com.slicedwork.slicedwork.enum

import com.slicedwork.slicedwork.R

enum class FieldErrors {
    EMPTY {
        override fun getStateMessage(): Int {
            return R.string.obligate_field_empty
        }
    },
    NOT_VALID {
        override fun getStateMessage(): Int {
            return R.string.obligate_field_not_valid
        }
    };

    abstract fun getStateMessage(): Int
}