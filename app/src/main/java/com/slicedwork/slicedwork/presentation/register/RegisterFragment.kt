package com.slicedwork.slicedwork.presentation.register

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentRegisterBinding
import com.slicedwork.slicedwork.databinding.LayoutCameraGalleryButtonSheetBinding
import com.slicedwork.slicedwork.domain.factory.UserFactory
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.util.CameraUtil
import com.slicedwork.slicedwork.util.temporary.getGenders
import com.slicedwork.slicedwork.util.temporary.getYears

class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var bottomSheetBinding: LayoutCameraGalleryButtonSheetBinding
    private lateinit var cameraUtil: CameraUtil
    private var tempImageUri: Uri? = null
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private var user: User = UserFactory.createUserDefault()

    private var requestPermissionLauncher = registerForActivityResult(
        RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) Log.i("Debug", "Camera Permission has granted!")
        else Log.i("Debug", "Camera Permission has not granted!")
    }

    private var cameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) binding.ivProfile.setImageURI(tempImageUri)
        }

    private val selectPictureLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            binding.ivProfile.setImageURI(uri)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        bottomSheetBinding =
            LayoutCameraGalleryButtonSheetBinding.inflate(inflater, container, false)
        bottomSheetDialog = BottomSheetDialog(this.requireContext(), R.style.BottomSheetDialogTheme)
        cameraUtil = CameraUtil(this.requireContext())

        val actvBirthYearAdapter = ArrayAdapter(
            this.requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            getYears()
        )
        binding.actvBirthYear.setAdapter(actvBirthYearAdapter)

        val actvGenderAdapter = ArrayAdapter(
            this.requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            getGenders(this.requireContext())
        )
        binding.avtvGender.setAdapter(actvGenderAdapter)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnAddImage.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
        bottomSheetBinding.btnCamera.setOnClickListener(this)
        bottomSheetBinding.btnGallery.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view) {
            binding.btnAddImage -> {
                bottomSheetDialog.setContentView(bottomSheetBinding.bottomSheet)
                bottomSheetDialog.show()
            }
            bottomSheetBinding.btnCamera ->
                execBottomSheetEvents(Manifest.permission.CAMERA, CameraUtil.CAMERA)
            bottomSheetBinding.btnGallery ->
                execBottomSheetEvents(Manifest.permission.READ_EXTERNAL_STORAGE, CameraUtil.GALLERY)
            binding.btnRegister -> {
                user = UserFactory.createUserByBinding(binding, tempImageUri)

            }
        }
    }

    private fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this.requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun execBottomSheetEvents(permission: String, bottomSheetButton: Int) {
        if (hasPermission(permission)) {
            when (bottomSheetButton) {
                CameraUtil.CAMERA -> openCamera()
                CameraUtil.GALLERY -> openGallery()
            }
            bottomSheetDialog.dismiss()
        } else requestPermissionLauncher.launch(permission)
    }

    private fun openCamera() {
        tempImageUri = cameraUtil.getTempImageUri()
        cameraLauncher.launch(tempImageUri)
    }

    private fun openGallery() {
        selectPictureLauncher.launch("image/*")
    }
}
