package com.slicedwork.slicedwork.util.enumerator

import android.content.Context
import com.slicedwork.slicedwork.util.MediaUtil

enum class MediaEnum {
    CAMERA {
        override fun open(context: Context, mediaUtil: MediaUtil) {
            mediaUtil.openCamera(context)
        }
    },
    GALLERY {
        override fun open(context: Context, mediaUtil: MediaUtil) {
            mediaUtil.openGallery()
        }
    };

    abstract fun open(context: Context, mediaUtil: MediaUtil)
}