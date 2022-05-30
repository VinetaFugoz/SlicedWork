package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentHomeBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.adapter.VacancyAdapter
import com.slicedwork.slicedwork.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private lateinit var _activity: MainActivity
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        goToGreetings()
        setListeners()
        _activity.showToolbar()
        _activity.colorStatusBar(R.color.primaryDarkColor)
        viewModel.getVacancies()
        viewModel.vacanciesLiveData.observe(viewLifecycleOwner) { vacancies ->
            setVacancies(vacancies)
        }
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentHomeBinding.inflate(inflater)
        _activity = this.requireActivity() as MainActivity
    }

    private fun setVacancies(vacancies: List<Vacancy>) {
        _binding.rvVacancies.run {
            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
            adapter = VacancyAdapter(vacancies, requireContext()) { vacancy ->
                goToVacancyDetails(vacancy)
            }
        }
    }

    private fun setListeners() {
        _binding.fabAnnouncement.setOnClickListener { goToRegisterVacancy() }
    }

    private fun goToGreetings() {
        if (Firebase.auth.uid.isNullOrEmpty()) findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToGreetingsFragment())
    }

    private fun goToVacancyDetails(vacancy: Vacancy) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToVacancyDetailsFragment(vacancy)
        )
    }

    private fun goToRegisterVacancy() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRegisterVacancyGraph())
    }
}