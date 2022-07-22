package com.slicedwork.slicedwork.presentation.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.slicedwork.slicedwork.databinding.DialogCandidatesBinding
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.adapter.CandidatesAdapter
import com.slicedwork.slicedwork.presentation.viewmodel.CandidatesViewModel
import com.slicedwork.slicedwork.util.enumerator.CandidateStatusEnum.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CandidatesDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogCandidatesBinding
    private val viewModel: CandidatesViewModel by viewModels()
    private var candidates: List<Candidate> = listOf()
    private var usCandidates: MutableList<Pair<User, Candidate>> = mutableListOf()
    private lateinit var vacancy: Vacancy

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogCandidatesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        findNavController().navigate(CandidatesDialogDirections.actionCandidatesDialogToVacancyDetailsFragment(vacancy))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    override fun onResume() {
        super.onResume()
        vacancy = arguments?.get("vacancy") as Vacancy
        viewModel.getCandidatesById("vacancyId", vacancy.id)
    }

    private fun setObservers() {

        viewModel.isUpdatedLiveData.observe(viewLifecycleOwner) { isUpdated ->
            viewModel.getCandidatesById("vacancyId", vacancy.id)
        }

        viewModel.candidatesLiveData.observe(viewLifecycleOwner) { candidates ->
            this.candidates = candidates
            if (candidates.map { it.status }.contains(APPROVED.ordinal)) {
                for (candidate in candidates) {
                    if (candidate.status != APPROVED.ordinal) viewModel.updateCandidate(candidate.id, "status", DISAPPROVED.ordinal)
                }
            }
            val userIds = candidates.map { it.userId }
            getUserById(userIds)
        }

        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            binding.progressBar.visibility = View.GONE
            val usCandidate = getUscandidates(user)
            if (usCandidate != null) {
                if (usCandidates.map { it.second.id }.contains(usCandidate.second.id)) {
                    val index = usCandidates.map { it.second.id }.indexOf(usCandidate.second.id)
                    usCandidates[index] = usCandidate
                } else usCandidates.add(usCandidate)

                val approvedOneId: String? =
                    if (candidates.map { it.status }.contains(APPROVED.ordinal))
                        candidates[candidates.map { it.status }.indexOf(APPROVED.ordinal)].id
                    else null

                setCandidatesInRecycler(usCandidates, approvedOneId)
            }
        }
    }

    private fun getUscandidates(user: User): Pair<User, Candidate>? {
        val candidate = candidates.filter { it.userId == user.id }
        return if (candidate.isEmpty()) null else Pair(user, candidate[0])
    }

    private fun getUserById(userIds: List<String>) {
        for (userId in userIds) {
            viewModel.getUser(userId)
        }
    }

    private fun setCandidatesInRecycler(usCandidates: List<Pair<User, Candidate>>, approvedOneId: String?) {
        binding.rvCandidates.run {
            layoutManager = LinearLayoutManager(this@CandidatesDialog.requireContext())
            adapter = CandidatesAdapter(
                usCandidates,
                requireContext(),
                approvedOneId
            ) { document, field, value ->
                updateCandidate(document, field, value)
            }
        }
    }

    private fun updateCandidate(document: String, field: String, value: Int) {
        viewModel.updateCandidate(document, field, value)
    }
}