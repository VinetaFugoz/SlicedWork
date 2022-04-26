package com.slicedwork.slicedwork.presentation.fragment.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentGetPasswordBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.presentation.viewmodel.register.GetPasswordViewModel
import com.slicedwork.slicedwork.util.extensions.focusAndShowSoftKeyboard
import com.slicedwork.slicedwork.util.extensions.hideKeyBoard

class GetPasswordFragment : Fragment() {
    private lateinit var _binding: FragmentGetPasswordBinding
    private lateinit var _user: User
    private val _viewModel: GetPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)

        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        _binding.btnNext.setOnClickListener { onNextEvent(it)}
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetPasswordBinding.inflate(inflater)

        _binding.run {
            viewModel = _viewModel
            lifecycleOwner = viewLifecycleOwner
            tietPassword.focusAndShowSoftKeyboard(requireContext())
        }
    }

    private fun onNextEvent(view: View) {
        view.hideKeyBoard(requireContext())
        getUser()
        setUserProps()
        goToGetPicture()
    }

    private fun getUser() {
        _user = arguments?.get("user") as User
    }

    private fun setUserProps() {
        _user.password = _viewModel.passwordLiveData.value.toString()
    }

    private fun goToGetPicture() =
        findNavController().navigate(
            GetPasswordFragmentDirections.actionGetPasswordFragmentToGetPictureFragment(_user)
        )
}