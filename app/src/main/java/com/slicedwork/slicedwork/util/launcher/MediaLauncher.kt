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
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.presentation.dialog.ChooseCameraGalleryDialog
import java.io.File

class MediaLauncher(
    private val registry: ActivityResultRegistry,
    private val ChooseCameraGalleryDialog: ChooseCameraGalleryDialog
) : DefaultLifecycleObserver {

    var imageUri: Uri? = null
    var pictureWasTaken: Boolean = false

    lateinit var cameralauncher: ActivityResultLauncher<Uri>
    lateinit var gallerylauncher: ActivityResultLauncher<String>

    override fun onCreate(owner: LifecycleOwner) {
        gallerylauncher = registry.register("gallery", owner, GetContent()) {
            imageUri = it
            pictureWasTaken = (imageUri != null)
            goBack()
        }

        cameralauncher = registry.register("camera", owner, TakePicture()) {
            pictureWasTaken = it
            goBack()
        }
    }

    private fun goBack() {
        ChooseCameraGalleryDialog.findNavController().run {
            previousBackStackEntry?.savedStateHandle?.set(
                "imageUri", if (pictureWasTaken) imageUri.toString() else null)
            navigateUp()
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