package com.slicedwork.slicedwork.util.enumerator

enum class CollectionEnum {
    USER {
        override fun getText(): String = "/user"
    },
    VACANCY {
        override fun getText(): String = "/vacancy"
    };

    abstract fun getText(): String
}