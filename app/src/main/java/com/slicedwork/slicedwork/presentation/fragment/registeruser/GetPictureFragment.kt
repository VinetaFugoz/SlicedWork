package com.slicedwork.slicedwork.presentation.fragment.registeruser

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.slicedwork.slicedwork.databinding.DialogChooseCameraGalleryBinding
import com.slicedwork.slicedwork.databinding.FragmentGetPictureBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.util.enumerator.MediaEnum
import com.slicedwork.slicedwork.util.launcher.MediaLauncher
import com.slicedwork.slicedwork.util.launcher.PermissionLauncher

class GetPictureFragment : Fragment() {
    private lateinit var _binding: FragmentGetPictureBinding
    private lateinit var _bottomSheetDialogBinding: DialogChooseCameraGalleryBinding
    private lateinit var _bottomSheetDialog: BottomSheetDialog
    private lateinit var mediaLauncher: MediaLauncher
    private lateinit var permissionLauncher: PermissionLauncher
    private lateinit var permissions: Array<String>
    private lateinit var _user: User
    private var imageUriDefault: Uri? = null
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setLaunchers()
        addLifecycleObservers()
        setProps(inflater)
        requestMediaPermissions()

        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        setListeners()

    }

    private fun setLaunchers() {
        mediaLauncher = MediaLauncher(requireActivity().activityResultRegistry)
        permissionLauncher = PermissionLauncher(requireActivity().activityResultRegistry)
    }

    private fun addLifecycleObservers() {
        lifecycle.addObserver(mediaLauncher)
        lifecycle.addObserver(permissionLauncher)
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetPictureBinding.inflate(inflater)
        _bottomSheetDialogBinding = DialogChooseCameraGalleryBinding.inflate(inflater)
        _bottomSheetDialog = BottomSheetDialog(requireContext())
        imageUriDefault = Uri.parse("R.drawable.image")
        permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private fun requestMediaPermissions() =
        permissionLauncher.requestPermissions(permissions, requireContext())

    private fun setListeners() {
        _binding.run {
            btnAdd.setOnClickListener { onStartBottomSheetDialog() }
            btnNext.setOnClickListener { onNextEvent() }
        }
        _bottomSheetDialogBinding.run {
            btnCamera.setOnClickListener { onBottomSheetEvent(MediaEnum.CAMERA) }
            btnGallery.setOnClickListener { onBottomSheetEvent(MediaEnum.GALLERY) }
            checkAndSetIfImageWasReallyTaken()
        }
    }

    private fun onStartBottomSheetDialog() {
        _bottomSheetDialog.setContentView(_bottomSheetDialogBinding.layoutRoot)
        _bottomSheetDialog.show()
    }

    private fun onBottomSheetEvent(mediaEnum: MediaEnum) {
        setMediaProps(mediaEnum)

        openMedia(mediaEnum)

        _bottomSheetDialog.dismiss()
    }

    private fun openMedia(mediaEnum: MediaEnum) {
        mediaEnum.open(requireContext(), mediaLauncher)
    }

    private fun setMediaProps(mediaEnum: MediaEnum) {
        mediaLauncher.run {
            if (mediaEnum == MediaEnum.GALLERY) imageUri = null
            pictureWasTaken = false
        }
    }

    private fun checkAndSetIfImageWasReallyTaken() {
        mediaLauncher.run {
            if (imageUri != null && pictureWasTaken) {
                this@GetPictureFragment.imageUri = imageUri
                _binding.ivPicture.setImageURI(imageUri)
            }
        }
    }

    private fun onNextEvent() {
        getUser()
        setUserProps()
        goToGetEmail()
    }

    private fun getUser() {
        _user = arguments?.get("user") as User
    }

    private fun setUserProps() {
        _user.picture =
            (if (imageUri != null) imageUri!! else imageUriDefault!!).toString()
    }

    private fun goToGetEmail() =
        findNavController().navigate(
            GetPictureFragmentDirections.actionGetPictureFragmentToFinishSignUpFragment(_user)
        )
}