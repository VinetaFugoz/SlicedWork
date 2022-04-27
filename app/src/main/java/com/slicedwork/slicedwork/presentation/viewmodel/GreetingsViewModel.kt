package com.slicedwork.slicedwork.presentation.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.slicedwork.slicedwork.presentation.fragment.GreetingsFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GreetingsViewModel @Inject constructor() : ViewModel() {
}