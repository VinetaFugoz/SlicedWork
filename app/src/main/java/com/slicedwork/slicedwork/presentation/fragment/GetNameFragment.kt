package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentGetNameBinding
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.extensions.ViewExt.focusAndShowSoftKeyboard
import com.slicedwork.slicedwork.presentation.viewmodel.GetNameViewModel

class GetNameFragment : Fragment() {

    private lateinit var _binding: FragmentGetNameBinding
    private lateinit var _activity: MainActivity
    private val _viewModel: GetNameViewModel by viewModels()

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
        _binding.tietFirstName.focusAndShowSoftKeyboard(this.requireContext())
        _viewModel.fragment = this
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetNameBinding.inflate(inflater)
        _binding.viewModel = _viewModel
        _binding.lifecycleOwner = this.viewLifecycleOwner
        _viewModel.fragment = this
        _activity = this.requireActivity() as MainActivity
    }
}