package com.slicedwork.slicedwork.presentation.dialog

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.slicedwork.slicedwork.databinding.DialogChooseCameraGalleryBinding
import com.slicedwork.slicedwork.util.enumerator.MediaEnum
import com.slicedwork.slicedwork.util.launcher.MediaLauncher
import com.slicedwork.slicedwork.util.launcher.PermissionLauncher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChooseCameraGalleryDialog : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var imageUri: String
    private lateinit var _binding: DialogChooseCameraGalleryBinding
    private lateinit var _mediaLauncher: MediaLauncher
    private lateinit var _permissionLauncher: PermissionLauncher
    private lateinit var _permissions: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        setLaunchers()
        addLifecycleObservers()
        requestMediaPermissions()
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = DialogChooseCameraGalleryBinding.inflate(inflater)
        _permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private fun setLaunchers() {
        _mediaLauncher = MediaLauncher(requireActivity().activityResultRegistry, this)
        _permissionLauncher = PermissionLauncher(requireActivity().activityResultRegistry)
    }

    private fun addLifecycleObservers() {
        lifecycle.addObserver(_mediaLauncher)
        lifecycle.addObserver(_permissionLauncher)
    }

    private fun requestMediaPermissions() =
        _permissionLauncher.requestPermissions(_permissions, requireContext())

    private fun setListeners() {
        _binding.run {
            btnCamera.setOnClickListener(this@ChooseCameraGalleryDialog)
            btnGallery.setOnClickListener(this@ChooseCameraGalleryDialog)
        }
    }

    private fun setMediaProps(mediaEnum: MediaEnum) {
        _mediaLauncher.run {
            if (mediaEnum == MediaEnum.GALLERY) imageUri = null
            pictureWasTaken = false
        }
    }

    private fun onBottomSheetEvent(mediaEnum: MediaEnum) {
        setMediaProps(mediaEnum)

        openMedia(mediaEnum)
    }

    private fun openMedia(mediaEnum: MediaEnum) {
        mediaEnum.open(requireContext(), _mediaLauncher)
    }

    override fun onClick(view: View) {
        when (view) {
            _binding.btnCamera -> onBottomSheetEvent(MediaEnum.CAMERA)
            _binding.btnGallery -> onBottomSheetEvent(MediaEnum.GALLERY)
        }
    }

}