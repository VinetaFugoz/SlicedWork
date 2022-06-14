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

    private lateinit var binding: FragmentFinishRegisterUserBinding
    private lateinit var activity: MainActivity
    private val viewModel: FinishRegisterUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinishRegisterUserBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setProps()
        setFinishEvent()
        setRegisteredObserver()

        val user = getUser()
        registerUser(user)
    }

    private fun setProps() {
        activity = requireActivity() as MainActivity
        setActivityProps()
    }

    private fun getUser() = arguments?.get("user") as User

    private fun setActivityProps() = activity.hideToolbar()

    private fun setFinishEvent() = binding.btnFinish.setOnClickListener { finishEvent() }

    private fun finishEvent() = findNavController().navigateUp()

    private fun setRegisteredObserver() = viewModel.registeredLiveData
        .observe(viewLifecycleOwner) { isRegistered ->
            binding.run {
                progressBar.visibility = View.GONE

                if (isRegistered) {
                    tvTitle.visibility = View.VISIBLE
                    btnFinish.visibility = View.VISIBLE
                }
            }
        }

    private fun registerUser(user: User) = viewModel.registerUser(user)
}