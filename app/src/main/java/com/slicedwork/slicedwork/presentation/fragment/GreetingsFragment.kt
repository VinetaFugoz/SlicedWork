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

    private lateinit var binding: FragmentGreetingsBinding
    private lateinit var activity: MainActivity
    private val viewModel: GreetingsViewModel by viewModels()
    private var hasToExecuteAnimation = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGreetingsBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        goBackToHome()
        setProps()
        checkToStartAnimation()
        setEvents()
    }

    private fun goBackToHome() {
        if (!Firebase.auth.uid.isNullOrEmpty()) findNavController().navigateUp()
    }

    private fun setProps() {
        activity = requireActivity() as MainActivity
        setBindingProps()
        setActivityProps()
    }

    private fun setBindingProps() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setActivityProps() {
        activity.colorStatusBar(R.color.primaryDarkColor)
        activity.hideToolbar()
    }

    private fun checkToStartAnimation() {
        if (hasToExecuteAnimation) {
            startAnimation()
            hasToExecuteAnimation = false
        }
    }

    private fun startAnimation() = binding.layoutRoot.post { start(binding) }

    private fun setEvents() {
        binding.run {
            btnLogin.setOnClickListener { goToLogin() }
            btnRegister.setOnClickListener { goToRegister() }
        }
    }

    private fun goToLogin() = findNavController()
        .navigate(GreetingsFragmentDirections.actionGreetingsFragmentToLoginFragment())

    private fun goToRegister() = findNavController()
        .navigate(GreetingsFragmentDirections.actionGreetingsFragmentToRegisterGraph())
}