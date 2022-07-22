package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentAppliedVacanciesBinding
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.adapter.AppliedVacanciesAdapter
import com.slicedwork.slicedwork.presentation.viewmodel.AppliedVacanciesViewModel
import com.slicedwork.slicedwork.util.enumerator.CandidateStatusEnum
import com.slicedwork.slicedwork.util.enumerator.CandidateStatusEnum.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppliedVacanciesFragment : Fragment() {

    private var lastVacancy: Vacancy = Vacancy("")
    private lateinit var binding: FragmentAppliedVacanciesBinding
    private var searchCount: Int = 0
    private var candidates: List<Candidate> = listOf()
    private var vacandidates: MutableList<Pair<Vacancy, Candidate>> = mutableListOf()
    private val viewModel: AppliedVacanciesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppliedVacanciesBinding.inflate(inflater)

        setStatusEvent()
        setObservers()
        if (searchCount == 0) {
            setBindingProps()
            Firebase.auth.uid?.let { viewModel.getCandidatesById("userId", it) }
            searchCount++
        }

        return binding.root
    }

    private fun setBindingProps() {
        val statusArray = resources.getStringArray(R.array.applied_vacancies_status)
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
            ALL.ordinal -> setVacanciesInRecycler(vacandidates)
            IN_WAITING.ordinal -> setVacanciesInRecycler(geFilteredVacancies(IN_WAITING))
            APPROVED.ordinal -> setVacanciesInRecycler(geFilteredVacancies(APPROVED))
            DISAPPROVED.ordinal -> setVacanciesInRecycler(geFilteredVacancies(DISAPPROVED))
        }
    }

    private fun geFilteredVacancies(status: CandidateStatusEnum): List<Pair<Vacancy, Candidate>> =
        vacandidates.filter { it.second.status == status.ordinal }

    private fun setObservers() {
        viewModel.candidatesLiveData.observe(viewLifecycleOwner) { candidates ->
            if (candidates.isNotEmpty()) {
                this.candidates = candidates
                val vacanciesId = candidates.map { it.vacancyId }
                getVacancyById(vacanciesId)
            } else binding.tvHint.visibility = View.VISIBLE
        }

        viewModel.vacancyLiveData.observe(viewLifecycleOwner) { vacancy ->
            if (vacancy != lastVacancy) {
                binding.rvVacancies.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                getVacandidates(vacancy)?.let {
                    vacandidates.add(it)
                    setVacanciesInRecycler(vacandidates)
                    lastVacancy = vacancy
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.candidatesLiveData.removeObservers(this)
        viewModel.vacancyLiveData.removeObservers(this)
        vacandidates = mutableListOf()
    }

    private fun getVacandidates(vacancy: Vacancy): Pair<Vacancy, Candidate>? {
        val candidate = candidates.filter { it.vacancyId == vacancy.id }
        if (!candidate.isNullOrEmpty()) return Pair(vacancy, candidate[0])
        return null
    }

    private fun getVacancyById(vacanciesId: List<String>) {
        for (vacancyId in vacanciesId) {
            viewModel.getVacancyById(vacancyId)
        }
    }

    private fun setVacanciesInRecycler(vacandidates: List<Pair<Vacancy, Candidate>>) {
        binding.rvVacancies.run {
            layoutManager = LinearLayoutManager(this@AppliedVacanciesFragment.requireContext())
            adapter = AppliedVacanciesAdapter(vacandidates, requireContext()) { vacancy ->
                goToVacancyDetails(vacancy)
            }
        }
    }

    private fun goToVacancyDetails(vacancy: Vacancy) {
        findNavController().navigate(
            AppliedVacanciesFragmentDirections
                .actionAppliedVacanciesFragmentToVacancyDetailsFragment(vacancy)
        )
    }
}