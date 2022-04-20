package com.slicedwork.slicedwork.util.enumerator

import android.content.Context
import com.slicedwork.slicedwork.util.launcher.MediaLauncher

enum class MediaEnum {
    CAMERA {
        override fun open(context: Context, mediaLauncher: MediaLauncher) {
            mediaLauncher.openCamera(context)
        }
    },
    GALLERY {
        override fun open(context: Context, mediaLauncher: MediaLauncher) {
            mediaLauncher.openGallery()
        }
    };

    abstract fun open(context: Context, mediaLauncher: MediaLauncher)
}