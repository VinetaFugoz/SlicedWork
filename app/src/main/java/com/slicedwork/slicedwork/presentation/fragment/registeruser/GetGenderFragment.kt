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

class GetGenderFragment : Fragment() {
    private lateinit var _binding: FragmentGetGenderBinding
    private val viewModel: GetGenderViewModel by viewModels()
    private lateinit var _user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)

        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        _binding.btnNext.setOnClickListener { onNextEvent() }
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetGenderBinding.inflate(inflater)
        _binding.viewModel = viewModel
        _binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun onNextEvent() {
        getUser()
        setUserProps()
        goToGetPhoneNumber()
    }

    private fun getUser() {
        _user = arguments?.get("user") as User
    }

    private fun setUserProps() {
        _user.gender = viewModel.checkedRadioLiveData.value?.toInt() ?: 0
    }

    private fun goToGetPhoneNumber() =
        findNavController().navigate(
            GetGenderFragmentDirections.actionGetGenderFragmentToGetPhoneNumberFragment(_user)
        )
}