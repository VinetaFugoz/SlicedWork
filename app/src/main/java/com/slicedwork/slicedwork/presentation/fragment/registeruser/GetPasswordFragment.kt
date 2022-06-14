package com.slicedwork.slicedwork.presentation.fragment.registeruser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentGetPasswordBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.presentation.viewmodel.registeruser.GetPasswordViewModel
import com.slicedwork.slicedwork.util.extensions.focusAndShowSoftKeyboard
import com.slicedwork.slicedwork.util.extensions.hideKeyboard

class GetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentGetPasswordBinding
    private val viewModel: GetPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetPasswordBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setBindingProps()
        setEvents()
    }

    private fun setEvents() {
        binding.btnNext.setOnClickListener { nextEvent() }
    }

    private fun setBindingProps() {
        binding.run {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            tietPassword.focusAndShowSoftKeyboard(requireContext())
        }
    }

    private fun nextEvent() {
        hideKeyboard()

        val user = getUser()
        user.password = getUserPassword()
        goToGetPicture(user)
    }

    private fun hideKeyboard() = binding.root.hideKeyboard(requireContext())

    private fun getUser() = arguments?.get("user") as User

    private fun getUserPassword() = viewModel.passwordLiveData.value.toString()

    private fun goToGetPicture(user: User) =
        findNavController().navigate(
            GetPasswordFragmentDirections.actionGetPasswordFragmentToGetPictureFragment(user)
        )
}