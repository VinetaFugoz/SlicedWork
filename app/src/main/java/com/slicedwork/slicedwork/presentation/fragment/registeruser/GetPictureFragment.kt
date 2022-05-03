package com.slicedwork.slicedwork.presentation.fragment.registeruser

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentGetPictureBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.util.extensions.navigate

class GetPictureFragment : Fragment() {
    private lateinit var _binding: FragmentGetPictureBinding
    private lateinit var _user: User
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)

        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        setListeners()
        getDialogBackArgs()
    }

    private fun getDialogBackArgs() {
        findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("imageUri")
            ?.observe(viewLifecycleOwner) { imageUri ->
                if (imageUri != null) {
                    this.imageUri = Uri.parse(imageUri)
                    _binding.ivPicture.scaleType = ImageView.ScaleType.CENTER_CROP
                    _binding.ivPicture.setImageURI(this.imageUri)
                }
            }
    }

    private fun goToChooseCameraGallery() {
        navigate(GetPictureFragmentDirections.actionGetPictureFragmentToChooseCameraGalleryDialog())
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetPictureBinding.inflate(inflater)
    }

    private fun setListeners() {
        _binding.run {
            btnAdd.setOnClickListener { goToChooseCameraGallery() }
            btnNext.setOnClickListener { onNextEvent() }
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
        _user.picture = imageUri.toString()
    }

    private fun goToGetEmail() =
        findNavController().navigate(
            GetPictureFragmentDirections.actionGetPictureFragmentToFinishSignUpFragment(_user)
        )
}