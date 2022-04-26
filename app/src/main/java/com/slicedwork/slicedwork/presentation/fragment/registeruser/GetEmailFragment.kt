package com.slicedwork.slicedwork.presentation.fragment.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentGetEmailBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.presentation.viewmodel.register.GetEmailViewModel
import com.slicedwork.slicedwork.util.extensions.focusAndShowSoftKeyboard
import com.slicedwork.slicedwork.util.extensions.hideKeyBoard

class GetEmailFragment : Fragment() {
    private lateinit var _binding: FragmentGetEmailBinding
    private lateinit var _user: User
    private val _viewModel: GetEmailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)

        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        _binding.btnNext.setOnClickListener { onNextEvent(it) }
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetEmailBinding.inflate(inflater)

        _binding.run {
            viewModel = _viewModel
            lifecycleOwner = viewLifecycleOwner
            tietEmail.focusAndShowSoftKeyboard(requireContext())
        }
    }

    private fun onNextEvent(view: View) {
        view.hideKeyBoard(requireContext())
        getUser()
        setUserProps()
        goToGetEmail()
    }

    private fun getUser() {
        _user = arguments?.get("user") as User
    }

    private fun setUserProps() {
        _user.email = _viewModel.emailLiveData.value.toString()
    }

    private fun goToGetEmail() =
        findNavController().navigate(
            GetEmailFragmentDirections.actionGetEmailFragmentToGetPasswordFragment(_user)
        )
}