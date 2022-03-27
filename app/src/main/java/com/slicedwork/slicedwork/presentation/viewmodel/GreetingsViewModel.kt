package com.slicedwork.slicedwork.presentation.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.slicedwork.slicedwork.util.enumerator.DestinationsEnum.GREETINGS_SIGN_IN
import com.slicedwork.slicedwork.util.enumerator.DestinationsEnum.GREETINGS_SIGN_UP
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GreetingsViewModel @Inject constructor(): ViewModel() {
    fun goToSignIn(view: View) = view.findNavController().navigate(GREETINGS_SIGN_IN.getDirection())
    fun goToSignUp(view: View) = view.findNavController().navigate(GREETINGS_SIGN_UP.getDirection())
}