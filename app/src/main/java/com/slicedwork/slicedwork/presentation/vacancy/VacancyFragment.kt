package com.slicedwork.slicedwork.presentation.vacancy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.domain.model.Vacancy
import com.slicedwork.slicedwork.databinding.FragmentVacancyBinding
import com.slicedwork.slicedwork.util.temporary.getUserById

class VacancyFragment : Fragment() {

    private lateinit var binding: FragmentVacancyBinding
    private lateinit var vacancy: Vacancy
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentVacancyBinding.inflate(inflater, container, false)
        vacancy = VacancyFragmentArgs.fromBundle(requireArguments()).vacancy
        setComponents()
        return binding.root
    }

    private fun setComponents() {
        binding.apply {
            ivOccupationArea.setImageResource(R.drawable.rakanandxayahpenguin)
            incDetails.apply {
                tvPrice.text = vacancy.price
                tvOccupationArea.text = vacancy.occupationArea
                tvTask.text = vacancy.task
                tvDescription.text = vacancy.description
            }
            incLocation.apply {
                tvPostalCode.text = vacancy.postalCode
                tvState.text = vacancy.state
                tvCity.text = vacancy.city
                tvDistrict.text = vacancy.district
                tvPublicPlace.text = vacancy.publicPlace
                tvNumber.text = vacancy.number
            }
            incAdvertiser.apply {
                tvUsername.text = user.username
                tvActiveSince.text = getString(R.string.lbl_active_since)
            }
        }
    }
}