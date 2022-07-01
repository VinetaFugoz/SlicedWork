package com.slicedwork.slicedwork.presentation.fragment.registeruser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentGetPhoneNumberBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.presentation.viewmodel.registeruser.GetPhoneNumberViewModel
import com.slicedwork.slicedwork.util.extensions.focusAndShowSoftKeyboard
import com.slicedwork.slicedwork.util.extensions.hideKeyboard

class GetPhoneNumberFragment : Fragment() {

    private lateinit var binding: FragmentGetPhoneNumberBinding
    private lateinit var user: User
    private val viewModel: GetPhoneNumberViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetPhoneNumberBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setBindingProps()
        binding.btnNext.setOnClickListener { nextView() }
    }

    private fun setBindingProps() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.tietPhoneNumber.focusAndShowSoftKeyboard(requireContext())
    }

    private fun nextView() {
        hideKeyboard()

        user = getUser()
        user.phoneNumber = getUserPhoneNumber()
        goToGetNickEmailPassword()
    }

    private fun hideKeyboard() = binding.root.hideKeyboard(requireContext())

    private fun getUser() = arguments?.get("user") as User

    private fun getUserPhoneNumber() = viewModel.phoneNumberLiveData.value.toString()

    private fun goToGetNickEmailPassword() =
        findNavController().navigate(
            GetPhoneNumberFragmentDirections.actionGetPhoneNumberFragmentToGetNicknameFragment(user)
        )
}