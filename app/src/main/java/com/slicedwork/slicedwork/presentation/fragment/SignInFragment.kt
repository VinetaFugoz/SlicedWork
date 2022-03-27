package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentSignInBinding
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.SignInViewModel

class SignInFragment : Fragment() {

    private lateinit var _binding: FragmentSignInBinding
    private lateinit var _activity: MainActivity
    private val _viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        _activity.colorStatusBar(R.color.primaryDarkColor)
        _activity.hideToolbar()
    }

    override fun onStop() {
        super.onStop()
        _activity.showToolbar()
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentSignInBinding.inflate(inflater)
        _binding.viewmodel = _viewModel
        _binding.lifecycleOwner = this.viewLifecycleOwner
        _activity = this.requireActivity() as MainActivity
    }
}