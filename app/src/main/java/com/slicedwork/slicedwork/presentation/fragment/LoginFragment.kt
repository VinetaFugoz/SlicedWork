package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentLoginBinding
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var activity: MainActivity
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity = this.requireActivity() as MainActivity
        setBindingProps()
        setActivityProps()
        setLoginEvent()
        setLoggedObserver()
    }

    private fun setBindingProps() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun setActivityProps() {
        activity.run {
            colorToolBar(R.color.transparent)
            showToolbar()
        }
    }

    private fun setLoginEvent() {
        binding.btnLogin.setOnClickListener { loginEvent() }
    }

    private fun setLoggedObserver() {
        viewModel.loggedLiveData.observe(viewLifecycleOwner) { logged ->
            binding.progressBar.visibility = View.GONE
            if (logged == true){
                goBackToGreetings()
            }
        }
    }

    private fun loginEvent() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.run {
            loginUser(emailLiveData.value.toString(), passwordLiveData.value.toString())
        }
    }

    private fun goBackToGreetings() {
        findNavController().navigateUp()
    }
}