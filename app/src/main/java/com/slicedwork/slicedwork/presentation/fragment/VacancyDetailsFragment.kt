package com.slicedwork.slicedwork.presentation.fragment

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
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.presentation.viewmodel.VacancyDetailsViewModel
import com.slicedwork.slicedwork.util.OccupationAreaUtil.getOccupationAreaString
import com.slicedwork.slicedwork.util.enumerator.CollectionEnum
import com.slicedwork.slicedwork.util.enumerator.CollectionEnum.VACANCY
import com.slicedwork.slicedwork.util.enumerator.FieldEnum
import com.slicedwork.slicedwork.util.enumerator.FieldEnum.STATUS
import com.slicedwork.slicedwork.util.enumerator.VacancyStatusEnum.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VacancyDetailsFragment : Fragment() {

    private lateinit var binding: FragmentVacancyDetailsBinding
    private lateinit var vacancy: Vacancy
    private var user: User? = null
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
        if (Firebase.auth.uid != vacancy.userId) binding.ivEditStatus.visibility = View.GONE
        else setHasOptionsMenu(true)
    }

    private fun getVacancy(): Vacancy = requireArguments().get("vacancy") as Vacancy

    private fun getDialogBackArgsObserver() {
        this.findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Boolean>("updated")
            ?.observe(viewLifecycleOwner) { updated ->
                if (updated) viewModel.getVacancyById(vacancy.id)
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
        binding.tvGoToProfile.setOnClickListener { goToProfile() }
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

    private fun goToProfile() {
        findNavController().navigate(
            VacancyDetailsFragmentDirections.actionVacancyDetailsFragmentToProfileFragment(user)
        )
    }

    private fun setObservers() {
        getDialogBackArgsObserver()

        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            this.user = user
            setVacancyProps()
        }

        viewModel.vacancyLiveData.observe(viewLifecycleOwner) { vacancy ->
            this.vacancy = vacancy
            setVacancyProps()
        }

        viewModel.deletedLiveData.observe(viewLifecycleOwner) { deleted ->
            if (deleted) findNavController().navigateUp()
        }
    }

    private fun getUser() {
        viewModel.getUser(vacancy.userId)
    }

    private fun setVacancyProps() {
        binding.run {
            Glide.with(requireContext()).load(vacancy.picture).centerCrop().into(ivPicture)
            tvTask.text = vacancy.task
            val signPrice = "R$ ${vacancy.price}"
            tvPrice.text = signPrice
            if (vacancy.description != "") tvDescription.text = vacancy.description
            else {
                tvDescriptionLabel.visibility = View.GONE
                tvDescription.visibility = View.GONE
            }
            tvOccupationArea.text = getOccupationAreaString(vacancy.occupationArea, requireContext())
            val localization =
                "${getString(R.string.get_address_street)} ${vacancy.street} ${vacancy.number}, ${vacancy.neighborhood}, ${vacancy.city} - ${vacancy.state}"

            tvStatus.text = when (vacancy.status) {
                OPENED.ordinal -> OPENED.getText(requireContext())
                CLOSED.ordinal -> CLOSED.getText(requireContext())
                else -> FINISHED.getText(requireContext())
            }

            tvLocalization.text = localization

            Glide.with(requireContext()).load(user?.picture).circleCrop().into(ivUserPicture)
            val fullname = "${user?.firstName} ${user?.lastName}"
            tvName.text = fullname
            tvUsername.text = user?.username
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