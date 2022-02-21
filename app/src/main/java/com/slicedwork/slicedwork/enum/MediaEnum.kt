package com.slicedwork.slicedwork.enum

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
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