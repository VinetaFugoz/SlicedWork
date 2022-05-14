package com.slicedwork.slicedwork.presentation.fragment.registervacancy

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentGetDetailsBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.registervacancy.GetDetailsViewModel
import com.slicedwork.slicedwork.util.enumerator.OccupationAreaEnum.*
import com.slicedwork.slicedwork.util.extensions.navigate
import com.slicedwork.slicedwork.util.validator.VacancyValidator
import java.util.*

class GetDetailsFragment : Fragment() {

    private lateinit var _activity: MainActivity
    private var imageUri: String? = null
    private lateinit var _binding: FragmentGetDetailsBinding
    private val _viewModel: GetDetailsViewModel by viewModels()
    private lateinit var _vacancy: Vacancy
    private lateinit var _vacancyValidator: VacancyValidator

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
        _activity.showToolbar()
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetDetailsBinding.inflate(inflater)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = viewLifecycleOwner
        val occupationAreas = resources.getStringArray(R.array.get_details_occupation_areas)
        val occupationAreaAdapter = ArrayAdapter(
            this.requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            occupationAreas
        )
        _activity = this.requireActivity() as MainActivity
        _binding.actvOccupationArea.setAdapter(occupationAreaAdapter)
        _vacancyValidator = VacancyValidator()
    }

    private fun setListeners() {
        _binding.run {
            btnAdd.setOnClickListener { goToChooseCameraGallery() }
            actvOccupationArea.setOnItemClickListener { _, _, position, _ ->
                if (imageUri == null) occupationAreaEvent(position)
            }
            btnNext.setOnClickListener { nextEvent() }
        }
    }

    private fun goToChooseCameraGallery() {
        navigate(GetDetailsFragmentDirections.actionGetDetailsFragmentToChooseCameraGalleryDialog())
    }

    private fun getDialogBackArgs() {
        findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("imageUri")
            ?.observe(viewLifecycleOwner) { imageUri ->
                if (imageUri != null) {
                    this.imageUri = imageUri
                    _binding.ivPicture.scaleType = ImageView.ScaleType.CENTER_CROP
                    _binding.ivPicture.setImageURI(Uri.parse(imageUri))
                }
            }
    }

    private fun occupationAreaEvent(position: Int) {
        _binding.run {
            ivPicture.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    when (position) {
                        0 -> PAINTING.getImage()
                        1 -> CLEANING.getImage()
                        2 -> GARDENING.getImage()
                        3 -> CONSTRUCTION.getImage()
                        4 -> ELECTRIC.getImage()
                        else -> PLUMBING.getImage()
                    }
                )
            )
        }
    }

    private fun nextEvent() {
        if (validateFields()) {
            createVacancy()
            goToGetAddress()
        }
    }

    private fun validateFields(): Boolean {
        var isValidate = true

        _viewModel.run {
            _binding.run {
                if (taskErrorLiveData.value == true) {
                    tilTask.error = getString(R.string.get_details_task_error)
                    isValidate = false
                }
                if (occupationAreaErrorLiveData.value == true) {
                    tilOccupationArea.error = getString(R.string.get_details_task_error)
                    isValidate = false
                }
                if (priceErrorLiveData.value == true) {
                    tilPrice.error = getString(R.string.get_details_task_error)
                    isValidate = false
                }
            }
        }

        return isValidate
    }

    private fun createVacancy() {
        _binding.run {
            if (Firebase.auth.currentUser != null)
                _vacancy = Vacancy(
                    id = UUID.randomUUID().toString(),
                    uuid = Firebase.auth.currentUser!!.uid,
                    task = tietTask.text.toString(),
                    description = tietDescription.text.toString(),
                    occupationArea = actvOccupationArea.text.toString(),
                    picture = imageUri.toString()
                )
        }
    }

    private fun goToGetAddress() {
        findNavController().navigate(
            GetDetailsFragmentDirections.actionGetDetailsFragmentToGetAddressFragment(_vacancy)
        )
    }
}