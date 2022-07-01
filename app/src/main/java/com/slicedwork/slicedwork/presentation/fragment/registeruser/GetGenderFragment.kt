package com.slicedwork.slicedwork.presentation.fragment.registeruser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentGetGenderBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.presentation.viewmodel.registeruser.GetGenderViewModel
import com.slicedwork.slicedwork.util.enumerator.GenderEnum.*
import com.slicedwork.slicedwork.util.extensions.hideKeyboard

class GetGenderFragment : Fragment() {

    private lateinit var binding: FragmentGetGenderBinding
    private val viewModel: GetGenderViewModel by viewModels()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetGenderBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProps()
        setNextEvent()
    }

    private fun setProps() {
        setBindingProps()
    }

    private fun setBindingProps() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setNextEvent() {
        binding.btnNext.setOnClickListener { nextEvent() }
    }

    private fun nextEvent() {
        hideKeyBoard()
        user = getUser()
        user.gender = getUserGender()
        goToGetPhoneNumber(user)
    }

    private fun hideKeyBoard() = binding.root.hideKeyboard(requireContext())

    private fun getUser() = arguments?.get("user") as User

    private fun getUserGender() = viewModel.checkedRadioLiveData.value?.toInt() ?: MALE.ordinal

    private fun goToGetPhoneNumber(user: User) = findNavController().navigate(
        GetGenderFragmentDirections.actionGetGenderFragmentToGetPhoneNumberFragment(user)
    )
}