package com.slicedwork.slicedwork.util

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File

class CameraUtil(val context: Context) {

    private var tempImageUri: Uri? = null
    private var tempImageFilePath = ""

    companion object {
        const val CAMERA = 0
        const val GALLERY = 1
    }

    fun getTempImageUri(): Uri? {
        return FileProvider.getUriForFile(
            context,
            "com.slicedwork.slicedwork.provider",
            createImageFile().also { file ->
                file.absolutePath
            })
    }

    private fun createImageFile(): File {
        val storageDir = this.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("temp_image", ".jpg", storageDir)
    }
}