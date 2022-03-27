package com.slicedwork.slicedwork.util.enumerator

import androidx.navigation.NavDirections
import com.slicedwork.slicedwork.presentation.fragment.GreetingsFragmentDirections

enum class DestinationsEnum {
    GREETINGS_SIGN_IN {
        override fun getDirection() = GreetingsFragmentDirections
                .actionGreetingsFragmentToSignInFragment()
    },
    GREETINGS_SIGN_UP {
        override fun getDirection() = GreetingsFragmentDirections
            .actionGreetingsFragmentToSignUpFragment()
    };

    abstract fun getDirection(): NavDirections
}