package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.slicedwork.slicedwork.databinding.FragmentGetBirthdayBinding

class GetBirthdayFragment : BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentGetBirthdayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetBirthdayBinding.inflate(inflater, container, false)

        _binding.npFirst.minValue = 0
        _binding.npFirst.maxValue = 59

        return _binding.root
    }
}