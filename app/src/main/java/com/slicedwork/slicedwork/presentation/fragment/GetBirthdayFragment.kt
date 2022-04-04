package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentGetBirthdayBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.util.DateUtil
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetBirthdayFragment : Fragment(), NumberPicker.OnValueChangeListener, View.OnClickListener {

    private lateinit var _binding: FragmentGetBirthdayBinding
    private val _user: User = GetBirthdayFragmentArgs.Companion.fromBundle(requireArguments()).user

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetBirthdayBinding.inflate(inflater, container, false)
        setProps()

        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        _binding.run {
            npDay.maxValue = DateUtil.getLastDayOfTheMonth(newVal, npYear.value)
        }
    }

    override fun onClick(view: View) {
        setUserProps()
        goToGetGender(view)
    }

    private fun setProps() {
        setYear()
        setMonth()
        setDay()
    }

    private fun setYear() {
        _binding.run {
            npYear.maxValue = DateUtil.getYear() - 18
            npYear.minValue = 1905
            npYear.wrapSelectorWheel = false
            npYear.value = npYear.maxValue
        }
    }

    private fun setMonth() {
        _binding.run {
            val months = resources.getStringArray(R.array.months)
            npMonth.minValue = 0
            npMonth.maxValue = months.size - 1
            npMonth.displayedValues = months
            npMonth.wrapSelectorWheel = false
            npMonth.value = DateUtil.getMonth()
        }
    }

    private fun setDay() {
        _binding.run {
            npDay.minValue = 1
            npDay.maxValue = DateUtil.getLastDayOfTheMonth(npMonth.value, npYear.value)
            npDay.value = DateUtil.getDay()
        }
    }

    private fun setListeners() {
        _binding.npMonth.setOnValueChangedListener(this@GetBirthdayFragment)
        _binding.npYear.setOnValueChangedListener(this@GetBirthdayFragment)
        _binding.btnNext.setOnClickListener(this@GetBirthdayFragment)
    }

    private fun setUserProps() {
        _binding.run {
        val format = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = LocalDate.parse("${npDay.value}-${npMonth.value}-${npYear.value}" , format)


            _user.birthDate = date.getLong()
        }
    }

    private fun goToGetGender(view: View) {
        view.findNavController().navigate(GetBirthdayFragmentDirections.actionGetBirthdayFragmentToGetGenderFragment(_user))
    }
}