package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentAnnouncedVacanciesBinding
import com.slicedwork.slicedwork.databinding.FragmentHomeBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.adapter.VacancyAdapter
import com.slicedwork.slicedwork.presentation.viewmodel.AnnouncedViewModel
import com.slicedwork.slicedwork.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnnouncedVacanciesFragment : Fragment() {

    private lateinit var _binding: FragmentAnnouncedVacanciesBinding
    private lateinit var _activity: MainActivity
    private val viewModel: AnnouncedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        viewModel.vacanciesLiveData.observe(viewLifecycleOwner) { vacancies ->
            setVacancies(vacancies)
        }
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getVacancies()
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentAnnouncedVacanciesBinding.inflate(inflater)
    }

    private fun setVacancies(vacancies: List<Vacancy>) {
        _binding.rvVacancies.run {
            layoutManager = LinearLayoutManager(this@AnnouncedVacanciesFragment.requireContext())
            adapter = VacancyAdapter(vacancies, requireContext()) { vacancy ->
                goToVacancyDetails(vacancy)
            }
        }
    }

    private fun goToVacancyDetails(vacancy: Vacancy) {
        findNavController().navigate(AnnouncedVacanciesFragmentDirections
                .actionAnnouncedVacanciesFragmentToVacancyDetailsFragment(vacancy)
        )
    }
}