package com.slicedwork.slicedwork.presentation.fragment.registeruser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentGetBirthdayBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.util.extensions.hideKeyboard
import com.slicedwork.slicedwork.util.getDay
import com.slicedwork.slicedwork.util.getLastDayOfTheMonth
import com.slicedwork.slicedwork.util.getMonth
import com.slicedwork.slicedwork.util.getYear

class GetBirthdayFragment : Fragment() {

    private lateinit var binding: FragmentGetBirthdayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetBirthdayBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProps()
        setEvents()
    }

    private fun setProps() {
        setYearProps()
        setMonthProps()
        setDayProps()
    }

    private fun getUser() = arguments?.get("user") as User

    private fun setYearProps() = binding.run {
            npYear.maxValue = getYear() - 18
            npYear.minValue = 1905
            npYear.wrapSelectorWheel = false
            npYear.value = npYear.maxValue
        }

    private fun setMonthProps() = binding.run {
            val months = resources.getStringArray(R.array.get_birthday_months)
            npMonth.minValue = 0
            npMonth.maxValue = months.size - 1
            npMonth.displayedValues = months
            npMonth.wrapSelectorWheel = false
            npMonth.value = getMonth()
        }

    private fun setDayProps() = binding.run {
            npDay.minValue = 1
            npDay.maxValue = getLastDayOfTheMonth(npMonth.value, npYear.value)
            npDay.value = getDay()
        }

    private fun setEvents() = binding.run {
            npMonth.setOnValueChangedListener { _,_, value -> numberPickerEvent(value)}
            npYear.setOnValueChangedListener { _,_, value -> numberPickerEvent(value) }
            btnNext.setOnClickListener { nextEvent() }
        }

    private fun numberPickerEvent(value: Int) = binding.run {
        npDay.maxValue = getLastDayOfTheMonth(value, npYear.value)
    }

    private fun nextEvent() {
        hideKeyboard()
        val user = getUser()
        user.birthDate = getUserBirthDate()
        goToGetGender(user)
    }

    private fun hideKeyboard() = binding.root.hideKeyboard(requireContext())

    private fun getUserBirthDate() = binding.run {
        "${npDay.value}-${npMonth.value}-${npYear.value}"
    }

    private fun goToGetGender(user: User) {
        findNavController().navigate(
            GetBirthdayFragmentDirections.actionGetBirthdayFragmentToGetGenderFragment(user)
        )
    }
}