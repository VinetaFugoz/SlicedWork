package com.slicedwork.slicedwork.presentation.fragment.registeruser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.databinding.FragmentFinishRegisterUserBinding
import com.slicedwork.slicedwork.domain.model.User
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.registeruser.FinishRegisterUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinishRegisterUserFragment : Fragment() {

    private lateinit var _binding: FragmentFinishRegisterUserBinding
    private lateinit var _activity: MainActivity
    private lateinit var _user: User
    private val _viewModel: FinishRegisterUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)

        _viewModel.registeredLiveData.observe(viewLifecycleOwner) { registered ->
            if (registered == true) _binding.btnFinish.visibility = View.VISIBLE
        }

        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        _binding.btnFinish.setOnClickListener { findNavController().navigateUp() }
        _viewModel.registerUser(_user)

    }

    private fun getUser() = arguments?.get("user") as User

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentFinishRegisterUserBinding.inflate(inflater)
        _activity = requireActivity() as MainActivity
        _activity.hideToolbar()
        _user = getUser()
    }
}