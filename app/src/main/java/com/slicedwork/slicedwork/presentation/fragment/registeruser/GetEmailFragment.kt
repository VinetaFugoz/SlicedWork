package com.slicedwork.slicedwork.presentation.fragment.registeruser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentGetEmailBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.presentation.viewmodel.registeruser.GetEmailViewModel
import com.slicedwork.slicedwork.util.extensions.focusAndShowSoftKeyboard
import com.slicedwork.slicedwork.util.extensions.hideKeyboard

class GetEmailFragment : Fragment() {
    private lateinit var binding: FragmentGetEmailBinding
    private val viewModel: GetEmailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetEmailBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProps()
        setNextEvent()
    }

    private fun setNextEvent() = binding.btnNext.setOnClickListener { nextEvent() }

    private fun setProps() {
        setBindingProps()
    }

    private fun setBindingProps() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.tietEmail.focusAndShowSoftKeyboard(requireContext())

    }

    private fun nextEvent() {
        hideKeyboard()

        val user = getUser()
        user.email = getUserEmail()
        goToGetPassword(user)
    }

    private fun hideKeyboard() = binding.root.hideKeyboard(requireContext())

    private fun getUser() = arguments?.get("user") as User

    private fun getUserEmail() = viewModel.emailLiveData.value.toString()

    private fun goToGetPassword(user: User) =
        findNavController().navigate(
            GetEmailFragmentDirections.actionGetEmailFragmentToGetPasswordFragment(user)
        )
}