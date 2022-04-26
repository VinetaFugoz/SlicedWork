package com.slicedwork.slicedwork.presentation.fragment.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentGetUsernameBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.presentation.viewmodel.register.GetNicknameViewModel
import com.slicedwork.slicedwork.util.extensions.focusAndShowSoftKeyboard
import com.slicedwork.slicedwork.util.extensions.hideKeyBoard

class GetUsernameFragment : Fragment() {

    private lateinit var _binding: FragmentGetUsernameBinding
    private lateinit var _user: User
    private val _viewModel: GetNicknameViewModel by viewModels()

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

    private fun onNextEvent(view: View) {
        view.hideKeyBoard(requireContext())
        getUser()
        setUserProps()
        goToGetEmail()
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetUsernameBinding.inflate(inflater)

        _binding.run {
            viewModel = _viewModel
            lifecycleOwner = viewLifecycleOwner
            tietNickname.focusAndShowSoftKeyboard(requireContext())
        }
    }

    private fun getUser() {
        _user = arguments?.get("user") as User
    }

    private fun setUserProps() {
        _user.nickname = _viewModel.nicknameLiveData.value.toString()
    }

    private fun goToGetEmail() =
        findNavController().navigate(
            GetUsernameFragmentDirections.actionGetUsernameFragmentToGetEmailFragment(_user)
        )
}