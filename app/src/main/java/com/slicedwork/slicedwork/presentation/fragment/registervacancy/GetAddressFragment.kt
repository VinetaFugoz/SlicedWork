package com.slicedwork.slicedwork.presentation.fragment.registervacancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentGetAddressBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.viewmodel.registervacancy.GetAddressViewModel

class GetAddressFragment : Fragment() {

    private lateinit var _binding: FragmentGetAddressBinding
    private lateinit var _vacancy: Vacancy
    private val _viewModel: GetAddressViewModel by viewModels()

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
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetAddressBinding.inflate(inflater)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setListeners() {
        _binding.run {
            btnNext.setOnClickListener { nextEvent() }
        }
    }

    private fun nextEvent() {
        if (validateFields()) {
            getVacancy()
            setVacancyProps()
            goToGetFinishRegisterVacancy()
        }
    }

    private fun validateFields(): Boolean {
        var isValidate = true

        _viewModel.run {
            _binding.run {
                if (countryErrorLiveData.value == true) {
                    tilCountry.error = getString(R.string.get_details_task_error)
                    isValidate = false
                }
                if (stateErrorLiveData.value == true) {
                    tilState.error = getString(R.string.get_details_task_error)
                    isValidate = false
                }
                if (cityErrorLiveData.value == true) {
                    tilCity.error = getString(R.string.get_details_task_error)
                    isValidate = false
                }
                if (neighborhoodErrorLiveData.value == true) {
                    tilNeighborhood.error = getString(R.string.get_details_task_error)
                    isValidate = false
                }
                if (postalCodeErrorLiveData.value == true) {
                    tilPostalCode.error = getString(R.string.get_details_task_error)
                    isValidate = false
                }
                if (streetErrorLiveData.value == true) {
                    tilStreet.error = getString(R.string.get_details_task_error)
                    isValidate = false
                }
                if (numberErrorLiveData.value == true) {
                    tilNumber.error = getString(R.string.get_details_task_error)
                    isValidate = false
                }
            }
        }

        return isValidate
    }

    private fun getVacancy() {
        _vacancy = arguments?.get("vacancy") as Vacancy
    }

    private fun setVacancyProps() {
        _binding.run {
            _vacancy.country = tietCountry.text.toString()
            _vacancy.state = tietState.text.toString()
            _vacancy.city = tietCity.text.toString()
            _vacancy.neighborhood = tietNeighborhood.text.toString()
            _vacancy.postalCode = tietPostalCode.text.toString()
            _vacancy.street = tietStreet.text.toString()
            _vacancy.number = tietNumber.text.toString()
        }
    }

    private fun goToGetFinishRegisterVacancy() {
        findNavController().navigate(
            GetAddressFragmentDirections.actionGetAddressFragmentToFinishRegisterVacancyFragment(
                _vacancy
            )
        )
    }
}