package com.slicedwork.slicedwork.presentation.fragment.registervacancy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentFinishRegisterUserBinding
import com.slicedwork.slicedwork.databinding.FragmentFinishRegisterVacancyBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.registeruser.FinishRegisterUserViewModel
import com.slicedwork.slicedwork.presentation.viewmodel.registervacancy.FinishRegisterVacancyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinishRegisterVacancyFragment : Fragment() {

    private lateinit var _binding: FragmentFinishRegisterVacancyBinding
    private lateinit var _activity: MainActivity
    private lateinit var _vacancy: Vacancy
    private val _viewModel: FinishRegisterVacancyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        _binding.btnFinish.setOnClickListener { findNavController().navigateUp() }
        _viewModel.registerVacancy(_vacancy)
    }

    private fun getVacancy() = arguments?.get("vacancy") as Vacancy

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentFinishRegisterVacancyBinding.inflate(inflater)
        _activity = requireActivity() as MainActivity
        _activity.hideToolbar()
        _vacancy = getVacancy()
    }
}