package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentSignInBinding
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.RegisterViewModel

class SignInFragment : Fragment() {

    private lateinit var _binding: FragmentSignInBinding
    private lateinit var _activity: MainActivity
    private val _viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    private fun setListeners() {
        _binding.btnSignIn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentSignInBinding.inflate(inflater)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = this.viewLifecycleOwner
        _activity = this.requireActivity() as MainActivity
        _activity.hideToolbar()
    }
}