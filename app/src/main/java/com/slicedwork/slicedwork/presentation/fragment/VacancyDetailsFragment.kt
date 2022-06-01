package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class VacancyDetailsFragment : Fragment() {

    private lateinit var _vacancy: Vacancy
    private lateinit var _binding: FragmentVacancyDetailsBinding
    private var _user: User? = null
    private val viewModel: VacancyDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        setLiveData()
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    private fun setListeners() {
        _binding.tvGoToProfile.setOnClickListener { goToProfile() }
    }

    private fun goToProfile() {
        findNavController().navigate(
            VacancyDetailsFragmentDirections.actionVacancyDetailsFragmentToProfileFragment(_user)
        )
    }

    private fun setLiveData() {
        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            _user = user
            setVacancyProps()
        }
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentVacancyDetailsBinding.inflate(inflater)
        _vacancy = getVacancy()
        viewModel.getUser(_vacancy.userId)
    }

    private fun setVacancyProps() {
        _binding.run {
            Glide.with(requireContext()).load(_vacancy.picture).centerCrop().into(ivPicture)
            tvTask.text = _vacancy.task
            val signPrice = "R$ ${_vacancy.price}"
            tvPrice.text = signPrice
            if (_vacancy.description != "") tvDescription.text = _vacancy.description
            else {
                tvDescriptionLabel.visibility = View.GONE
                tvDescription.visibility = View.GONE
            }
            tvOccupationArea.text = _vacancy.occupationArea
            val localization =
                "${R.string.get_address_street} ${_vacancy.street} ${_vacancy.number}\n${_vacancy.neighborhood}\n${_vacancy.city} - ${_vacancy.state}"

            tvLocalization.text = localization

            Glide.with(requireContext()).load(_user?.picture).circleCrop().into(ivUserPicture)
            val fullname = "${_user?.firstName} ${_user?.lastName}"
            tvName.text = fullname
            tvUsername.text = _user?.username
        }
    }

    private fun getVacancy(): Vacancy = requireArguments().get("vacancy") as Vacancy
}