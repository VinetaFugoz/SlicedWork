package com.slicedwork.slicedwork.presentation.fragment.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentFinishSignUpBinding

class FinishSignUpFragment : Fragment() {

    private lateinit var _binding: FragmentFinishSignUpBinding

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
        _binding.btnNext.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentFinishSignUpBinding.inflate(inflater)
    }
}