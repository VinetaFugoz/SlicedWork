package com.slicedwork.slicedwork.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.slicedwork.slicedwork.R
import com.slicedwork.slicedwork.databinding.FragmentGreetingsBinding
import com.slicedwork.slicedwork.presentation.activity.MainActivity
import com.slicedwork.slicedwork.presentation.viewmodel.GreetingsViewModel
import com.slicedwork.slicedwork.util.animation.GreetingsAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GreetingsFragment : Fragment() {

    private lateinit var _binding: FragmentGreetingsBinding
    private lateinit var _activity: MainActivity
    private val _viewModel: GreetingsViewModel by viewModels()
    private var _hasToExecuteAnimation = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setProps(inflater)
        checkToStartAnimation()
        return _binding.root
    }

    override fun onResume() {
        super.onResume()
        _activity.colorStatusBar(R.color.primaryDarkColor)
        _activity.hideToolbar()
    }

    override fun onStop() {
        super.onStop()
        _activity.showToolbar()
    }

    private fun setProps(inflater: LayoutInflater) {
        _binding = FragmentGreetingsBinding.inflate(inflater)
        _binding.viewmodel = _viewModel
        _binding.lifecycleOwner = this.viewLifecycleOwner
        _activity = this.requireActivity() as MainActivity
    }

    private fun checkToStartAnimation() {
        if (_hasToExecuteAnimation) {
            startAnimation()
            _hasToExecuteAnimation = false
        }
    }

    private fun startAnimation() = _binding.layoutRoot.post { GreetingsAnimation.start(_binding) }
}