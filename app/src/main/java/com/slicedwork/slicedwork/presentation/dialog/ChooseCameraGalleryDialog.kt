package com.slicedwork.slicedwork.presentation.dialog

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.slicedwork.slicedwork.databinding.DialogChooseCameraGalleryBinding
import com.slicedwork.slicedwork.util.enumerator.MediaEnum
import com.slicedwork.slicedwork.util.launcher.MediaLauncher
import com.slicedwork.slicedwork.util.launcher.PermissionLauncher

class ChooseCameraGalleryDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogChooseCameraGalleryBinding
    private lateinit var mediaLauncher: MediaLauncher
    private lateinit var permissionLauncher: PermissionLauncher
    private lateinit var permissions: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogChooseCameraGalleryBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProps()
        setEvents()
        requestMediaPermissions()
    }

    override fun onStart() {
        super.onStart()
        setLaunchers()
        setObservers()
    }

    private fun setProps() {
        permissions = getPermissions()
    }

    private fun getPermissions(): Array<String> = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

    private fun setLaunchers() {
        mediaLauncher = MediaLauncher(requireActivity().activityResultRegistry, this)
        permissionLauncher = PermissionLauncher(requireActivity().activityResultRegistry)
    }

    private fun setObservers() {
        lifecycle.addObserver(mediaLauncher)
        lifecycle.addObserver(permissionLauncher)
    }

    private fun requestMediaPermissions() =
        permissionLauncher.requestPermissions(permissions, requireContext())

    private fun setEvents() {
        binding.run {
            btnCamera.setOnClickListener { onBottomSheetEvent(MediaEnum.CAMERA) }
            btnGallery.setOnClickListener { onBottomSheetEvent(MediaEnum.GALLERY) }
        }
    }

    private fun setMediaProps(mediaEnum: MediaEnum) {
        mediaLauncher.run {
            if (mediaEnum == MediaEnum.GALLERY) imageUri = null
            pictureWasTaken = false
        }
    }

    private fun onBottomSheetEvent(mediaEnum: MediaEnum) {
        setMediaProps(mediaEnum)
        openMedia(mediaEnum)
    }

    private fun openMedia(mediaEnum: MediaEnum) {
        mediaEnum.open(requireContext(), mediaLauncher)
    }
}