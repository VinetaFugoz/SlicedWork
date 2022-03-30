package com.slicedwork.slicedwork.util.enumerator

import androidx.navigation.NavDirections
import com.slicedwork.slicedwork.presentation.fragment.GetNameFragmentDirections
import com.slicedwork.slicedwork.presentation.fragment.GreetingsFragmentDirections

enum class DestinationsEnum {
    GREETINGS_SIGN_IN {
        override fun getDirection() = GreetingsFragmentDirections
                .actionGreetingsFragmentToSignInFragment()
    },
    GREETINGS_SIGN_UP {
        override fun getDirection() = GreetingsFragmentDirections
            .actionGreetingsFragmentToSignUp()
    },
    GET_NAME_GET_BIRTHDAY {
        override fun getDirection() = GetNameFragmentDirections
            .actionGetNameFragmentToGetBirthdayFragment()
    };

    abstract fun getDirection(): NavDirections
}