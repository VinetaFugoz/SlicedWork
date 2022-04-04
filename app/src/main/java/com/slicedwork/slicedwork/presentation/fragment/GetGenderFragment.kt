package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slicedwork.slicedwork.databinding.FragmentGetGenderBinding

class GetGenderFragment : Fragment() {
    private lateinit var binding: FragmentGetGenderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGetGenderBinding.inflate(inflater, container, false)

        return binding.root
    }
}