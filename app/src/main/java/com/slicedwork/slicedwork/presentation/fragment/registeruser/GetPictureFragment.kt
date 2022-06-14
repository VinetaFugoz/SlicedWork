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
import com.slicedwork.slicedwork.util.extensions.hideKeyboard
import com.slicedwork.slicedwork.util.extensions.navigate

class GetPictureFragment : Fragment() {
    private lateinit var binding: FragmentGetPictureBinding
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetPictureBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setEvents()
        getDialogBackArgs()
    }

    private fun setEvents() {
        binding.run {
            btnAdd.setOnClickListener { goToChooseCameraGallery() }
            btnNext.setOnClickListener { nextEvent() }
        }
    }

    private fun getDialogBackArgs() {
        findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("imageUri")
            ?.observe(viewLifecycleOwner) { imageUri ->
                if (imageUri != null) {
                    this.imageUri = Uri.parse(imageUri)
                    binding.ivPicture.scaleType = ImageView.ScaleType.CENTER_CROP
                    binding.ivPicture.setImageURI(this.imageUri)
                }
            }
    }

    private fun goToChooseCameraGallery() {
        navigate(GetPictureFragmentDirections.actionGetPictureFragmentToChooseCameraGalleryDialog())
    }

    private fun nextEvent() {
        hideKeyboard()

        val user = getUser()
        user.picture = getUserPicture()
        goToGetEmail(user)
    }

    private fun hideKeyboard() = binding.root.hideKeyboard(requireContext())

    private fun getUser() = arguments?.get("user") as User

    private fun getUserPicture() = imageUri.toString()

    private fun goToGetEmail(user: User) =
        findNavController().navigate(
            GetPictureFragmentDirections.actionGetPictureFragmentToFinishSignUpFragment(user)
        )
}