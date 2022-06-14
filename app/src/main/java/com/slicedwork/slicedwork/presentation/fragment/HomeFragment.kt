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

    private lateinit var binding: FragmentHomeBinding
    private lateinit var activity: MainActivity
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (isLogged()) {
            setProps()
            setAnnouncementEvent()
            setVacanciesObserver()
            getVacancies()
        } else goToGreetings()
    }

    private fun setProps() {
        activity = requireActivity() as MainActivity
        setActivityProps()
    }

    private fun setActivityProps() {
        activity.showToolbar()
        activity.colorStatusBar(R.color.primaryDarkColor)
    }

    private fun isLogged(): Boolean = !Firebase.auth.uid.isNullOrEmpty()

    private fun setAnnouncementEvent() {
        binding.fabAnnouncement.setOnClickListener { goToRegisterVacancy() }
    }

    private fun setVacanciesObserver() {
        viewModel.vacanciesLiveData.observe(viewLifecycleOwner) { vacancies ->
            binding.run {
                rvVacancies.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }

            setVacanciesInRecycler(vacancies)
        }
    }

    private fun setVacanciesInRecycler(vacancies: List<Vacancy>) {
        binding.rvVacancies.run {
            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
            adapter = VacancyAdapter(vacancies, requireContext()) { vacancy ->
                goToVacancyDetails(vacancy)
            }
        }
    }

    private fun getVacancies() = viewModel.getVacancies()

    private fun goToGreetings() {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToGreetingsFragment()
        )
    }

    private fun goToVacancyDetails(vacancy: Vacancy) {
        findNavController().navigate(HomeFragmentDirections
                .actionHomeFragmentToVacancyDetailsFragment(vacancy)
        )
    }

    private fun goToRegisterVacancy() {
        findNavController().navigate(HomeFragmentDirections
                .actionHomeFragmentToRegisterVacancyGraph()
        )
    }
}