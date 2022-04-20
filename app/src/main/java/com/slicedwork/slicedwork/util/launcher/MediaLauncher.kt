package com.slicedwork.slicedwork.util.launcher

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.core.content.FileProvider
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.io.File

class MediaLauncher(private val registry: ActivityResultRegistry) : DefaultLifecycleObserver {

    var imageUri: Uri? = null
    var pictureWasTaken: Boolean = false

    lateinit var cameralauncher: ActivityResultLauncher<Uri>
    lateinit var gallerylauncher: ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        gallerylauncher = registry.register("gallery", owner, GetContent()) {
            imageUri = it
        }

        cameralauncher = registry.register("camera", owner, TakePicture()) {
            pictureWasTaken = it
        }
    }

    fun openGallery() {
        gallerylauncher.launch("image/*")
    }

    fun openCamera(context: Context) {
        imageUri = getImageUri(context)
        cameralauncher.launch(imageUri)
    }

    private fun getImageUri(context: Context): Uri? {
        return FileProvider.getUriForFile(
            context,
            "com.slicedwork.slicedwork.provider",
            createImageFile(context).also { file ->
                file.absolutePath
            })
    }

    private fun createImageFile(context: Context): File {
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("temp_image", ".jpg", storageDir)
    }
}