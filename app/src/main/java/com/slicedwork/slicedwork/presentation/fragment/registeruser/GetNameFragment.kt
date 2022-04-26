package com.slicedwork.slicedwork.presentation.fragment.register

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
import com.slicedwork.slicedwork.presentation.viewmodel.register.GetNameViewModel
import com.slicedwork.slicedwork.util.extensions.focusAndShowSoftKeyboard
import com.slicedwork.slicedwork.util.extensions.hideKeyBoard
import java.util.*

class GetNameFragment : Fragment() {

    private lateinit var _binding: FragmentGetNameBinding
    private lateinit var _activity: MainActivity
    private lateinit var _user: User
    private val _viewModel: GetNameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)

        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        _binding.btnNext.setOnClickListener { onNextEvent(it) }
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGetNameBinding.inflate(inflater)
        _activity = this.requireActivity() as MainActivity

        _binding.run {
            viewModel = _viewModel
            lifecycleOwner = viewLifecycleOwner
            tietFirstName.focusAndShowSoftKeyboard(requireContext())
        }

        _activity.run {
            colorToolBar(R.color.transparent)
            showToolbar()
        }
    }

    private fun onNextEvent(view: View) {
        view.hideKeyBoard(requireContext())
        createUser()
        setUserProps()
        goToGetBirthday()
    }

    private fun createUser() {
        _user = User(uuid = UUID.randomUUID().toString())
    }

    private fun setUserProps() {
        _user.firstName = _viewModel.firstNameLiveData.value.toString()
        _user.lastName = _viewModel.lastNameLiveData.value.toString()
    }

    private fun goToGetBirthday() =
        findNavController().navigate(
            GetNameFragmentDirections.actionGetNameFragmentToGetBirthdayFragment(
                _user
            )
        )
}