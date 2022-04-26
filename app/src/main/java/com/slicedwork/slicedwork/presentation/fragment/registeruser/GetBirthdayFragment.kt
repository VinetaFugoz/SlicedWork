package com.slicedwork.slicedwork.presentation.fragment.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentGetBirthdayBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.util.getDay
import com.slicedwork.slicedwork.util.getLastDayOfTheMonth
import com.slicedwork.slicedwork.util.getMonth
import com.slicedwork.slicedwork.util.getYear

class GetBirthdayFragment : Fragment(), NumberPicker.OnValueChangeListener {

    private lateinit var _binding: FragmentGetBirthdayBinding
    private lateinit var _user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)

        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        _binding.run {
            npDay.maxValue = getLastDayOfTheMonth(newVal, npYear.value)
        }
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetBirthdayBinding.inflate(inflater)
        setYear()
        setMonth()
        setDay()
    }

    private fun setYear() {
        _binding.run {
            npYear.maxValue = getYear() - 18
            npYear.minValue = 1905
            npYear.wrapSelectorWheel = false
            npYear.value = npYear.maxValue
        }
    }

    private fun setMonth() {
        _binding.run {
            val months = resources.getStringArray(R.array.get_birthday_months)
            npMonth.minValue = 0
            npMonth.maxValue = months.size - 1
            npMonth.displayedValues = months
            npMonth.wrapSelectorWheel = false
            npMonth.value = getMonth()
        }
    }

    private fun setDay() {
        _binding.run {
            npDay.minValue = 1
            npDay.maxValue = getLastDayOfTheMonth(npMonth.value, npYear.value)
            npDay.value = getDay()
        }
    }

    private fun setListeners() {
        _binding.run {
            npMonth.setOnValueChangedListener(this@GetBirthdayFragment)
            npYear.setOnValueChangedListener(this@GetBirthdayFragment)
            btnNext.setOnClickListener { onNextEvent() }
        }
    }

    private fun onNextEvent() {
        getUser()
        setUserProps()
        goToGetGender()
    }

    private fun getUser() {
        _user = arguments?.get("user") as User
    }

    private fun setUserProps() {
        _binding.run {
            _user.birthDate = "${npDay.value}-${npMonth.value}-${npYear.value}"
        }
    }

    private fun goToGetGender() {
        findNavController().navigate(
            GetBirthdayFragmentDirections.actionGetBirthdayFragmentToGetGenderFragment(_user)
        )
    }
}