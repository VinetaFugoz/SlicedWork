package com.slicedwork.slicedwork.presentation.register

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentRegisterBinding
import com.slicedwork.slicedwork.databinding.LayoutCameraGalleryButtonSheetBinding
import com.slicedwork.slicedwork.domain.factory.UserFactory
import com.slicedwork.slicedwork.domain.repository.UserRepositoryImpl
import com.slicedwork.slicedwork.enum.MediaEnum
import com.slicedwork.slicedwork.util.*
import com.slicedwork.slicedwork.util.temporary.getGenders
import com.slicedwork.slicedwork.util.temporary.getYears

class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var bottomSheetBinding: LayoutCameraGalleryButtonSheetBinding

    private lateinit var bottomSheetDialog: BottomSheetDialog

    private lateinit var registerViewModel: RegisterViewModel

    private lateinit var mediaUtil: MediaUtil
    private lateinit var permissionUtil: PermissionUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setBindings(inflater, container)
        setLifecycleObservers()
        setArrayAdapters()
        setViewModel()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setOnClickListeners()
    }

    override fun onClick(view: View) {
        when (view) {
            binding.btnAddImage -> onStartBottomSheetDialog()
            binding.btnRegister -> onRegister()
            bottomSheetBinding.btnCamera -> onBottomSheetEvents(
                Manifest.permission.CAMERA,
                MediaEnum.CAMERA, requireContext()
            )
            bottomSheetBinding.btnGallery -> {
                onBottomSheetEvents(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    MediaEnum.GALLERY, requireContext()
                )
            }
        }
        binding.ivProfile.setImageURI(mediaUtil.imageUri)
    }

    private fun goToHome() {
        RegisterFragmentDirections.actionGlobalHomeFragment()
    }

    private fun setBindings(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        bottomSheetBinding =
            LayoutCameraGalleryButtonSheetBinding.inflate(inflater, container, false)
        bottomSheetDialog = BottomSheetDialog(this.requireContext(), R.style.BottomSheetDialogTheme)
    }

    private fun setLifecycleObservers() {
        mediaUtil = MediaUtil(requireActivity().activityResultRegistry)
        permissionUtil = PermissionUtil(requireActivity().activityResultRegistry)

        lifecycle.addObserver(mediaUtil)
        lifecycle.addObserver(permissionUtil)
    }

    private fun setArrayAdapters() {
        binding.actvBirthYear.setAdapter(
            ArrayAdapter(
                this.requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                getYears()
            )
        )

        binding.avtvGender.setAdapter(
            ArrayAdapter(
                this.requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                getGenders(this.requireContext())
            )
        )
    }

    private fun setViewModel() {
        registerViewModel = ViewModelProvider(
            this,
            RegisterViewModelFactory(UserRepositoryImpl())
        )[RegisterViewModel::class.java]
    }

    private fun setOnClickListeners() {
        binding.btnAddImage.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
        bottomSheetBinding.btnCamera.setOnClickListener(this)
        bottomSheetBinding.btnGallery.setOnClickListener(this)
    }

    private fun onStartBottomSheetDialog() {
        bottomSheetDialog.setContentView(bottomSheetBinding.bottomSheet)
        bottomSheetDialog.show()
    }

    private fun onRegister() {
        binding.run {
            if (obligateFieldsIsValid(etEmail.text.toString(), etPassword.text.toString())) {
                registerViewModel.registerUser(
                    UserFactory.createUserByBinding(binding),
                    mediaUtil.imageUri
                )
                goToHome()
            }
        }
    }

    private fun onBottomSheetEvents(
        permission: String,
        mediaEnum: MediaEnum,
        context: Context,
    ) {
        permissionUtil.requestPermission(permission, context)
        mediaEnum.open(context, mediaUtil)
        bottomSheetDialog.dismiss()
    }

    private fun obligateFieldsIsValid(email: String, password: String): Boolean {
        var isValid = true
        if (isEmpty(email)) {
            isValid = false
            binding.etEmail.error = getString(R.string.obligate_field_empty)
        } else if (isEmailValidRegex(email)) {
            isValid = false
            binding.etEmail.error = getString(R.string.obligate_field_not_valid)
        }
        if (isEmpty(password)) {
            isValid = false
            binding.etPassword.error = getString(R.string.obligate_field_empty)
        }

        return isValid
    }
}