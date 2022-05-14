package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentLoginBinding
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding
    private lateinit var _activity: MainActivity
    private val _viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        _binding.btnLogin.setOnClickListener { loginEvent() }
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentLoginBinding.inflate(inflater)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun loginEvent() {
        _viewModel.run {
            loginUser(emailLiveData.value.toString(), passwordLiveData.value.toString())
        }

        goBackToGreetings()
    }

    private fun goBackToGreetings() {
        findNavController().navigateUp()
    }
}