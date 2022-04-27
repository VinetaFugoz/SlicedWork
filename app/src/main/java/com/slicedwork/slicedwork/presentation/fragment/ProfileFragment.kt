package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slicedwork.slicedwork.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var _binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        _binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigateUp()
        }
        return _binding.root
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentProfileBinding.inflate(inflater)
    }
}