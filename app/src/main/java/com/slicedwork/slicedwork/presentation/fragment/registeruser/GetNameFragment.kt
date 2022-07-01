package com.slicedwork.slicedwork.presentation.fragment.registeruser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentGetNameBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.registeruser.GetNameViewModel
import com.slicedwork.slicedwork.util.extensions.focusAndShowSoftKeyboard
import com.slicedwork.slicedwork.util.extensions.hideKeyboard

class GetNameFragment : Fragment() {

    private lateinit var binding: FragmentGetNameBinding
    private lateinit var activity: MainActivity
    private val viewModel: GetNameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetNameBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProps()
        setNextEvent()
    }

    private fun setProps() {
        activity = this.requireActivity() as MainActivity
        setBindingProps()
        setActivityProps()
    }

    private fun setBindingProps() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.tietFirstName.focusAndShowSoftKeyboard(requireContext())
    }

    private fun setActivityProps() {
        activity.run {
            colorToolBar(R.color.transparent)
            showToolbar()
        }
    }

    private fun setNextEvent() {
        binding.btnNext.setOnClickListener { btnNext -> nextEvent(btnNext) }
    }

    private fun nextEvent(btnNext: View) {
        hideKeyboard()
        val user = getUser()
        setUserProps(user)
        goToGetBirthday(user)
    }

    private fun hideKeyboard() = binding.root.hideKeyboard(requireContext())

    private fun getUser() = User()

    private fun setUserProps(user: User) {
        user.firstName = viewModel.firstNameLiveData.value.toString()
        user.lastName = viewModel.lastNameLiveData.value.toString()
    }

    private fun goToGetBirthday(user: User) =
        findNavController().navigate(
            GetNameFragmentDirections.actionGetNameFragmentToGetBirthdayFragment(user)
        )
}