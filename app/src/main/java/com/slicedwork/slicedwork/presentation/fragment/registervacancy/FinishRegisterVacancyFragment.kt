package com.slicedwork.slicedwork.presentation.fragment.registervacancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentFinishRegisterVacancyBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.registervacancy.FinishRegisterVacancyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinishRegisterVacancyFragment : Fragment() {

    private lateinit var binding: FragmentFinishRegisterVacancyBinding
    private lateinit var activity: MainActivity
    private lateinit var vacancy: Vacancy
    private val viewModel: FinishRegisterVacancyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinishRegisterVacancyBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProps()
        setFinishEvent()
        setRegisteredObserver()
        registerVacancy()
    }

    private fun setProps() {
        activity = requireActivity() as MainActivity
        vacancy = getVacancy()
        setActivityProps()
    }

    private fun getVacancy() = arguments?.get("vacancy") as Vacancy

    private fun setActivityProps() = activity.hideToolbar()

    private fun setFinishEvent() =
        binding.btnFinish.setOnClickListener { findNavController().navigateUp() }

    private fun setRegisteredObserver() {
        viewModel.isRegisteredLiveData.observe(viewLifecycleOwner) { isRegistered ->
            binding.run {
                progressBar.visibility = View.GONE

                if (isRegistered) {
                    tvTitle.visibility = View.VISIBLE
                    btnFinish.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun registerVacancy() = viewModel.registerVacancy(vacancy)
}