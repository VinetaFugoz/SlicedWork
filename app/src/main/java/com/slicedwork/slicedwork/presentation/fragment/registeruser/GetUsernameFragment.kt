package com.slicedwork.slicedwork.presentation.fragment.registeruser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentGetUsernameBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.presentation.viewmodel.registeruser.GetUsernameViewModel
import com.slicedwork.slicedwork.util.extensions.focusAndShowSoftKeyboard
import com.slicedwork.slicedwork.util.extensions.hideKeyboard

class GetUsernameFragment : Fragment() {

    private lateinit var binding: FragmentGetUsernameBinding
    private lateinit var user: User
    private val viewModel: GetUsernameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetUsernameBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setBindingProps()
        binding.btnNext.setOnClickListener { btnNext -> nextEvent(btnNext) }
    }

    private fun setBindingProps() {
        binding.run {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            tietNickname.focusAndShowSoftKeyboard(requireContext())
        }
    }

    private fun nextEvent(btnNext: View) {
        hideKeyboard()

        user = getUser()
        user.username = getUserUsername()
        goToGetEmail()
    }

    private fun hideKeyboard() = binding.root.hideKeyboard(requireContext())

    private fun getUser() = arguments?.get("user") as User

    private fun getUserUsername() = viewModel.usernameLiveData.value.toString()

    private fun goToGetEmail() =
        findNavController().navigate(
            GetUsernameFragmentDirections.actionGetUsernameFragmentToGetEmailFragment(user)
        )
}