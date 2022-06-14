package com.slicedwork.slicedwork.presentation.fragment.registervacancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentGetDetailsBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.registervacancy.GetDetailsViewModel
import com.slicedwork.slicedwork.util.OccupationAreaUtil.getOccupationAreaDrawable
import com.slicedwork.slicedwork.util.extensions.navigate
import java.util.*

class GetDetailsFragment : Fragment() {

    private lateinit var binding: FragmentGetDetailsBinding
    private val viewModel: GetDetailsViewModel by viewModels()
    private var imageUri: String? = null
    private var chosenOccupationArea: Int = 0
    private lateinit var activity: MainActivity
    private lateinit var occupationAreaAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetDetailsBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProps()
        setEvents()
        getDialogBackArgs()
    }

    private fun setProps() {
        activity = this.requireActivity() as MainActivity
        setBindingProps()
        setActivityProps()
    }

    private fun setBindingProps() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val occupationAreaArray = resources.getStringArray(R.array.occupation_areas)
        occupationAreaAdapter = getArrayAdapter(occupationAreaArray)
        binding.actvOccupationArea.setAdapter(occupationAreaAdapter)
    }

    private fun getArrayAdapter(statusArray: Array<String>) = ArrayAdapter(
        this.requireContext(),
        R.layout.support_simple_spinner_dropdown_item,
        statusArray
    )

    private fun setActivityProps() = activity.showToolbar()

    private fun setEvents() {
        binding.run {
            btnAdd.setOnClickListener { addPictureEvent() }
            btnNext.setOnClickListener { nextEvent() }
            actvOccupationArea.setOnItemClickListener { _, _, occupationArea, _ ->
                occupationAreaEvent(occupationArea)
            }
        }
    }

    private fun addPictureEvent() = goToChooseCameraGallery()

    private fun nextEvent() {
        if (isFieldsValidated()) {
            val vacancy = getVacancy()
            goToGetAddress(vacancy)
        }
    }

    private fun occupationAreaEvent(occupationArea: Int) {
        if (imageUri == null) {
            chosenOccupationArea = occupationArea
            binding.ivPicture.setImageDrawable(
                getOccupationAreaDrawable(occupationArea, requireContext())
            )
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

                    Glide.with(requireContext()).load(imageUri).centerCrop()
                        .into(binding.ivPicture)
                }
            }
    }

    private fun isFieldsValidated(): Boolean {

        viewModel.run {
            binding.run {
                if (taskErrorLiveData.value == true) {
                    tilTask.error = getString(R.string.get_details_task_error)
                    return false
                }
                if (occupationAreaErrorLiveData.value == true) {
                    tilOccupationArea.error = getString(R.string.get_details_task_error)
                    return false
                }
                if (priceErrorLiveData.value == true) {
                    tilPrice.error = getString(R.string.get_details_task_error)
                    return false
                }
            }
        }

        return true
    }

    private fun getVacancy() = binding.run {
            Vacancy(
                id = UUID.randomUUID().toString(),
                userId = Firebase.auth.currentUser!!.uid,
                task = tietTask.text.toString(),
                description = tietDescription.text.toString(),
                occupationArea = chosenOccupationArea,
                picture = imageUri.toString(),
                price = tietPrice.text.toString().toDouble(),
            )
        }
    

    private fun goToGetAddress(vacancy: Vacancy) {
        findNavController().navigate(
            GetDetailsFragmentDirections.actionGetDetailsFragmentToGetAddressFragment(vacancy)
        )
    }
}
