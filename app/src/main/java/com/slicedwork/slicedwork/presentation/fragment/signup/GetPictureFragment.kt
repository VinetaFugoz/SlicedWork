package com.slicedwork.slicedwork.presentation.fragment.signup

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.slicedwork.slicedwork.databinding.FragmentGetPictureBinding
import com.slicedwork.slicedwork.databinding.LayoutChooseCameraGalleryDialogBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.util.enumerator.MediaEnum
import com.slicedwork.slicedwork.util.launcher.MediaLauncher
import com.slicedwork.slicedwork.util.launcher.PermissionLauncher

class GetPictureFragment : Fragment() {
    private lateinit var _binding: FragmentGetPictureBinding
    private lateinit var _bottomSheetDialogBinding: LayoutChooseCameraGalleryDialogBinding
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
        setProps(inflater)
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    private fun setLaunchers() {
        mediaLauncher = MediaLauncher(requireActivity().activityResultRegistry)
        permissionLauncher = PermissionLauncher(requireActivity().activityResultRegistry)

        lifecycle.addObserver(mediaLauncher)
        lifecycle.addObserver(permissionLauncher)
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetPictureBinding.inflate(inflater)
        _bottomSheetDialogBinding = LayoutChooseCameraGalleryDialogBinding.inflate(inflater)
        _bottomSheetDialog = BottomSheetDialog(requireContext())
        imageUriDefault = Uri.parse("R.drawable.image")
        permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE)
        permissionLauncher.requestPermission(permissions, requireContext())
    }

    private fun setListeners() {
        _binding.run {
            btnAdd.setOnClickListener {
                onStartBottomSheetDialog()
            }
            btnNext.setOnClickListener {
                getUser()
                setUserProps()
                goToGetEmail()
            }
        }
        _bottomSheetDialogBinding.run {
            btnCamera.setOnClickListener {
                onBottomSheetEvent(MediaEnum.CAMERA,
                    requireContext()) }
            btnGallery.setOnClickListener {
                onBottomSheetEvent(MediaEnum.GALLERY,
                    requireContext()
                )
            }
            _binding.ivPicture.setImageURI(mediaLauncher.imageUri)
        }
    }

    private fun onStartBottomSheetDialog() {
        _bottomSheetDialog.setContentView(_bottomSheetDialogBinding.layoutRoot)
        _bottomSheetDialog.show()
    }

    private fun onBottomSheetEvent(mediaEnum: MediaEnum, context: Context) {
        mediaEnum.open(context, mediaLauncher)
        _bottomSheetDialog.dismiss()
    }

    private fun getUser() {
        _user = arguments?.get("user") as User
    }

    private fun setUserProps() {
        _user.picture = (if (imageUri != Uri.EMPTY) mediaLauncher.imageUri!! else imageUriDefault!!).toString()
    }

    private fun goToGetEmail() =
        findNavController().navigate(
            GetPictureFragmentDirections.actionGetPictureFragmentToFinishSignUpFragment(_user)
        )
}