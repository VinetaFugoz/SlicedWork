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

    private lateinit var binding: FragmentGetAddressBinding
    private val viewModel: GetAddressViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetAddressBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setBindingProps()
        setNextEvent()
    }

    private fun setBindingProps() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setNextEvent() = binding.run {
        btnNext.setOnClickListener { nextEvent() }
    }

    private fun nextEvent() {
        if (validateFields()) {
            val vacancy = getVacancy()
            setVacancyProps(vacancy)
            goToGetFinishRegisterVacancy(vacancy)
        }
    }

    private fun validateFields(): Boolean {
        var isValidate = true

        viewModel.run {
            binding.run {
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

    private fun getVacancy() = arguments?.get("vacancy") as Vacancy

    private fun setVacancyProps(vacancy: Vacancy) = binding.run {
        vacancy.country = tietCountry.text.toString()
        vacancy.state = tietState.text.toString()
        vacancy.city = tietCity.text.toString()
        vacancy.neighborhood = tietNeighborhood.text.toString()
        vacancy.postalCode = tietPostalCode.text.toString()
        vacancy.street = tietStreet.text.toString()
        vacancy.number = tietNumber.text.toString()
    }

    private fun goToGetFinishRegisterVacancy(vacancy: Vacancy) {
        findNavController().navigate(
            GetAddressFragmentDirections.actionGetAddressFragmentToFinishRegisterVacancyFragment(
                vacancy
            )
        )
    }
}