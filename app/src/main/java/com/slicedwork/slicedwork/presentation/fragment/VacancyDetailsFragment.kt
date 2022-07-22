package com.slicedwork.slicedwork.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentVacancyDetailsBinding
import com.slicedwork.slicedwork.domain.model.Candidate
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.viewmodel.VacancyDetailsViewModel
import com.slicedwork.slicedwork.util.OccupationAreaUtil.getOccupationAreaString
import com.slicedwork.slicedwork.util.enumerator.CandidateStatusEnum.*
import com.slicedwork.slicedwork.util.enumerator.CollectionEnum
import com.slicedwork.slicedwork.util.enumerator.CollectionEnum.VACANCY
import com.slicedwork.slicedwork.util.enumerator.FieldEnum
import com.slicedwork.slicedwork.util.enumerator.FieldEnum.STATUS
import com.slicedwork.slicedwork.util.enumerator.VacancyStatusEnum.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class VacancyDetailsFragment : Fragment() {

    private lateinit var binding: FragmentVacancyDetailsBinding
    private var candidate: Candidate? = null
    private var userWorker: User? = null
    private lateinit var vacancy: Vacancy
    private var userOwner: User? = null
    private val viewModel: VacancyDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVacancyDetailsBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProps()
        setEvents()
        setObservers()
        getUser()
    }

    private fun setProps() {
        vacancy = getVacancy()
        if (imVacancyOwner()) {
            setHasOptionsMenu(true)
            binding.btnApplyFor.visibility = View.GONE
            viewModel.getCandidate("status", APPROVED.ordinal, vacancy.id)
        } else {
            binding.ivEditStatus.visibility = View.GONE
            binding.btnSeeCandidates.visibility = View.GONE
            viewModel.getCandidate("userId", Firebase.auth.uid!!, vacancy.id)
        }
    }

    private fun imVacancyOwner(): Boolean = Firebase.auth.uid == vacancy.userId

    private fun getVacancy(): Vacancy = requireArguments().get("vacancy") as Vacancy

    private fun getDialogBackArgsObservers() {
        this.findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Boolean>("isFieldUpdated")
            ?.observe(viewLifecycleOwner) { isFieldUpdated ->
                if (isFieldUpdated) viewModel.getVacancyById(vacancy.id)
            }

        this.findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Boolean>("isCandidateStatusUpdated")
            ?.observe(viewLifecycleOwner) { isCandidateStatusUpdated ->
                if (isCandidateStatusUpdated) {
                    binding.progressBar.visibility = View.VISIBLE
                    onResume()
                }
            }
    }

    private fun setEvents() {
        binding.ivEditStatus.setOnClickListener {
            goToEditField(
                VACANCY,
                vacancy.id,
                STATUS,
                vacancy.status.toString()
            )
        }
        binding.tvGoToProfileOwner.setOnClickListener { goToProfile(userOwner) }
        binding.tvGoToProfileWorker.setOnClickListener { goToProfile(userWorker) }
        binding.btnApplyFor.setOnClickListener { applyForEvent() }
        binding.btnFinishVacancy.setOnClickListener { finishVacancyEvent() }
        binding.btnSeeCandidates.setOnClickListener { seeCandidatesEvent() }
    }

    private fun goToEditField(
        collection: CollectionEnum,
        document: String,
        field: FieldEnum,
        initialValue: String
    ) {
        findNavController().navigate(
            VacancyDetailsFragmentDirections.actionVacancyDetailsFragmentToEditFieldDialog(
                collection,
                document,
                field,
                initialValue
            )
        )
    }

    private fun goToProfile(user: User?) {
        findNavController().navigate(
            VacancyDetailsFragmentDirections.actionVacancyDetailsFragmentToProfileFragment(user)
        )
    }

    private fun applyForEvent() {
        val candidate = getCandidate()
        viewModel.registerCandidate(candidate)
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun getCandidate(): Candidate =
        Candidate(UUID.randomUUID().toString(), Firebase.auth.uid.toString(), vacancy.id, 1)

    private fun finishVacancyEvent() {
        viewModel.updateVacancy(
            true,
            vacancy.id,
            STATUS,
            FINISHED.getText(requireContext())
        )
    }

    private fun seeCandidatesEvent() {
        findNavController().navigate(VacancyDetailsFragmentDirections.actionVacancyDetailsFragmentToCandidatesDialog(vacancy))
    }

    private fun setObservers() {
        getDialogBackArgsObservers()

        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            if (user.id == candidate?.userId) this.userWorker = user
            else this.userOwner = user
            setVacancyProps()
        }

        viewModel.vacancyLiveData.observe(viewLifecycleOwner) { vacancy ->
            this.vacancy = vacancy
            setVacancyProps()
        }

        viewModel.deletedLiveData.observe(viewLifecycleOwner) { deleted ->
            if (deleted) findNavController().navigateUp()
        }

        viewModel.updatedLiveData.observe(viewLifecycleOwner) { updated ->
            if(updated) findNavController().navigateUp()
        }

        viewModel.isCandidateRegisteredLiveData.observe(viewLifecycleOwner) { isRegistered ->
            if (isRegistered) {
                binding.progressBar.visibility = View.GONE
                binding.btnApplyFor.visibility = View.GONE
            }
        }

        viewModel.candidateLiveData.observe(viewLifecycleOwner) { candidate ->
            if (candidate != null) {
                binding.btnApplyFor.visibility = View.GONE
                this.candidate = candidate
                viewModel.getUser(candidate.userId)
            }
        }
    }

    private fun getUser() {
        viewModel.getUser(vacancy.userId)
    }

    private fun setVacancyProps() {
        binding.run {
            Glide.with(requireContext()).load(vacancy.picture).centerCrop().into(ivPicture)
            tvTask.text = vacancy.task
            if (vacancy.description != "") tvDescription.text = vacancy.description
            else {
                tvDescriptionLabel.visibility = View.GONE
                tvDescription.visibility = View.GONE
            }
            tvOccupationArea.text =
                getOccupationAreaString(vacancy.occupationArea, requireContext())
            val localization =
                "${getString(R.string.get_address_street)} ${vacancy.street} ${vacancy.number}, ${vacancy.neighborhood}, ${vacancy.city} - ${vacancy.state}"

            tvStatus.text = when (vacancy.status) {
                OPENED.ordinal -> OPENED.getText(requireContext())
                CLOSED.ordinal -> { CLOSED.getText(requireContext()) }
                else -> FINISHED.getText(requireContext())
            }

            if (vacancy.status == CLOSED.ordinal) btnFinishVacancy.visibility = View.VISIBLE

            tvLocalization.text = localization

            Glide.with(requireContext()).load(userOwner?.picture).circleCrop()
                .into(incUserOwner.ivUserPicture)
            val fullname = "${userOwner?.firstName} ${userOwner?.lastName}"
            incUserOwner.tvName.text = fullname
            incUserOwner.tvUsername.text = userOwner?.username

            binding.progressBar.visibility = View.GONE
            binding.scrollView.visibility = View.VISIBLE

            if (userWorker != null && candidate?.status == APPROVED.ordinal) {
                Glide.with(requireContext()).load(userWorker?.picture).circleCrop()
                    .into(incUserWorker.ivUserPicture)
                val fullname = "${userWorker?.firstName} ${userWorker?.lastName}"
                incUserWorker.tvName.text = fullname
                incUserWorker.tvUsername.text = userWorker?.username

                tvWorkerLabel.visibility = View.VISIBLE
                tvGoToProfileWorker.visibility = View.VISIBLE
                incUserWorker.root.visibility = View.VISIBLE
                btnFinishVacancy.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.vacancy_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> deleteEvent()
        }
        return true
    }

    private fun deleteEvent() = viewModel.deleteVacancy(vacancy.id)
}