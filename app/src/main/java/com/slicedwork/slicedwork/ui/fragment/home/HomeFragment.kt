package com.slicedwork.slicedwork.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.slicedwork.slicedwork.data.model.Vacancy
import com.slicedwork.slicedwork.databinding.FragmentHomeBinding
import com.slicedwork.slicedwork.ui.adapter.VacanciesAdapter
import com.slicedwork.slicedwork.util.temporary.getVacancies

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        with(binding.rvVacancies) {
            adapter = VacanciesAdapter(getVacancies(), onItemCLickListener = { vacancy, view ->
                navigateToVacancy(vacancy, view)
            })
            layoutManager = LinearLayoutManager(this.context)
        }

        return binding.root
    }

    private fun navigateToVacancy(vacancy: Vacancy, view: View) {
        view.findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToVacancyFragment(vacancy))
    }
}