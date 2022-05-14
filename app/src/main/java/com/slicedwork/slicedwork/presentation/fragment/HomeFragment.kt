package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentHomeBinding
import com.slicedwork.slicedwork.presentation.activity.MainActivity

class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private lateinit var _activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        _binding.btnProfile.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
        }
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        goToGreetings()
        setListeners()
        _activity.showToolbar()
    }

    private fun setListeners() {
        _binding.fabAnnouncement.setOnClickListener { goToRegisterVacancy() }
    }

    private fun goToRegisterVacancy() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRegisterVacancyGraph())
    }

    private fun goToGreetings() {
        if (Firebase.auth.uid.isNullOrEmpty()) findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToGreetingsFragment())
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentHomeBinding.inflate(inflater)
        _activity = this.requireActivity() as MainActivity
        _activity.colorStatusBar(R.color.primaryDarkColor)
    }
}