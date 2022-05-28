package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentGreetingsBinding
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.GreetingsViewModel
import com.slicedwork.slicedwork.util.animation.start
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GreetingsFragment : Fragment() {

    private lateinit var _binding: FragmentGreetingsBinding
    private lateinit var _activity: MainActivity
    private val _viewModel: GreetingsViewModel by viewModels()
    private var _hasToExecuteAnimation = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        checkToStartAnimation()
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        goBackToHome()
        setListeners()
        _activity.hideToolbar()
    }

    override fun onStop() {
        super.onStop()
        _activity.showToolbar()
    }

    private fun goBackToHome() {
        if (!Firebase.auth.uid.isNullOrEmpty()) findNavController().navigateUp()
    }

    private fun setListeners() {
        _binding.run {
            btnLogin.setOnClickListener { goToLogin() }
            btnRegister.setOnClickListener { goToRegister() }
        }
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGreetingsBinding.inflate(inflater)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = this.viewLifecycleOwner
        _activity = this.requireActivity() as MainActivity
        _activity.colorStatusBar(R.color.primaryDarkColor)
    }

    private fun checkToStartAnimation() {
        if (_hasToExecuteAnimation) {
            startAnimation()
            _hasToExecuteAnimation = false
        }
    }

    private fun startAnimation() = _binding.layoutRoot.post { start(_binding) }

    private fun goToLogin() = findNavController()
        .navigate(GreetingsFragmentDirections.actionGreetingsFragmentToLoginFragment())

    private fun goToRegister() = findNavController()
        .navigate(GreetingsFragmentDirections.actionGreetingsFragmentToRegisterGraph())
}