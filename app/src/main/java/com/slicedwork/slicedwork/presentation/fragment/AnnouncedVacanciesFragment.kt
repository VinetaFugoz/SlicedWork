package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentAnnouncedVacanciesBinding
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.adapter.VacancyAdapter
import com.slicedwork.slicedwork.presentation.viewmodel.AnnouncedViewModel
import com.slicedwork.slicedwork.util.enumerator.VacancyStatusEnum
import com.slicedwork.slicedwork.util.enumerator.VacancyStatusEnum.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnnouncedVacanciesFragment : Fragment() {

    private lateinit var binding: FragmentAnnouncedVacanciesBinding
    private lateinit var vacancies: MutableList<Vacancy>
    private val viewModel: AnnouncedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnnouncedVacanciesBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setBindingProps()
        setStatusEvent()
        setVacanciesObserver()
        getVacancies()
    }

    private fun setBindingProps() {
        val statusArray = resources.getStringArray(R.array.announced_vacancies_status)
        val statusAdapter = getArrayAdapter(statusArray)
        binding.actvStatus.setAdapter(statusAdapter)
    }

    private fun getArrayAdapter(statusArray: Array<String>) = ArrayAdapter(
        this.requireContext(),
        R.layout.support_simple_spinner_dropdown_item,
        statusArray
    )

    private fun setStatusEvent() {
        binding.actvStatus.setOnItemClickListener { _, _, position, _ -> statusEvent(position) }
    }

    private fun statusEvent(position: Int) {
        when (position) {
            ALL.ordinal -> setVacanciesInRecycler(vacancies)
            OPENED.ordinal -> setVacanciesInRecycler(geFilteredVacancies(vacancies, OPENED))
            CLOSED.ordinal -> setVacanciesInRecycler(geFilteredVacancies(vacancies, CLOSED))
            FINISHED.ordinal -> setVacanciesInRecycler(geFilteredVacancies(vacancies, FINISHED))
        }
    }

    private fun geFilteredVacancies(
        vacancies: MutableList<Vacancy>, status: VacancyStatusEnum
    ): List<Vacancy> = vacancies.filter { vacancy -> vacancy.status == status.ordinal }

    private fun setVacanciesObserver() = viewModel.vacanciesLiveData
        .observe(viewLifecycleOwner) { vacancies ->
            binding.run {
                rvVacancies.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
            if (vacancies.isNotEmpty())
                this@AnnouncedVacanciesFragment.vacancies = vacancies as MutableList<Vacancy>
            setVacanciesInRecycler(vacancies)
        }

    private fun getVacancies() = viewModel.getVacancies()

    private fun setVacanciesInRecycler(vacancies: List<Vacancy>) {
        binding.rvVacancies.run {
            layoutManager = LinearLayoutManager(this@AnnouncedVacanciesFragment.requireContext())
            adapter = VacancyAdapter(vacancies, requireContext()) { vacancy ->
                goToVacancyDetails(vacancy)
            }
        }
    }

    private fun goToVacancyDetails(vacancy: Vacancy) {
        findNavController().navigate(
            AnnouncedVacanciesFragmentDirections
                .actionAnnouncedVacanciesFragmentToVacancyDetailsFragment(vacancy)
        )
    }
}