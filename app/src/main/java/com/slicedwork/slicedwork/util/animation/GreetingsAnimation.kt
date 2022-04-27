package com.slicedwork.slicedwork.util.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import com.slicedwork.slicedwork.databinding.FragmentGreetingsBinding

private fun translateY(
    view: View, animDuration: Long, fromValue: Float, toValue: Float
): AnimatorSet {
    val translate = AnimatorSet()
    translate.run {
        playTogether(
            ObjectAnimator.ofFloat(view, "translationY", fromValue, toValue)
        )
        duration = animDuration
    }

    return translate
}

private fun translateX(
    view: View, animDuration: Long, fromValue: Float, toValue: Float
): AnimatorSet {
    val translate = AnimatorSet()
    translate.run {
        playTogether(
            ObjectAnimator.ofFloat(view, "translationX", fromValue, toValue)
        )
        duration = animDuration
    }

    return translate
}

private fun scale(
    view: View,
    animDuration: Long = 0,
    fromValue: Float,
    toValue: Float
): AnimatorSet {
    val scale = AnimatorSet()

    scale.run {
        playTogether(
            ObjectAnimator.ofFloat(view, "scaleX", fromValue, toValue),
            ObjectAnimator.ofFloat(view, "scaleY", fromValue, toValue)
        )
        duration = animDuration
    }

    return scale
}

private fun fade(
    view: View,
    animDuration: Long,
    fromValue: Float,
    toValue: Float
): AnimatorSet {
    val fade = AnimatorSet()
    fade.run {
        play(ObjectAnimator.ofFloat(view, "alpha", fromValue, toValue))
        duration = animDuration
    }

    return fade
}

private fun partOne(binding: FragmentGreetingsBinding): AnimatorSet {
    val partOne = AnimatorSet()

    partOne.run {
        playTogether(
            fade(binding.ivBackground, 0, 1f, 0f),
            fade(binding.ivLogo, 0, 1f, 0f),
            fade(binding.tvTitle, 0, 1f, 0f),
            fade(binding.tvSubtitle, 0, 1f, 0f),
            fade(binding.btnLogin, 0, 1f, 0f),
            fade(binding.btnRegister, 0, 1f, 0f),
            fade(binding.tvS, 0, 1f, 0f),
            fade(binding.tvL, 0, 1f, 0f),
            fade(binding.tvI, 0, 1f, 0f),
            fade(binding.tvC, 0, 1f, 0f),
            fade(binding.tvE, 0, 1f, 0f),
            fade(binding.tvD, 0, 1f, 0f),
            fade(binding.tvW, 0, 1f, 0f),
            fade(binding.tvO, 0, 1f, 0f),
            fade(binding.tvR, 0, 1f, 0f),
            fade(binding.tvK, 0, 1f, 0f),
        )
    }

    return partOne
}

private fun partTwo(binding: FragmentGreetingsBinding): AnimatorSet {
    val partTwo = AnimatorSet()

    partTwo.run {
        playTogether(
            scale(binding.ivLogo, 0, 1f, 2f),
            scale(binding.tvS, 0, 1f, 2f),
            scale(binding.tvL, 0, 1f, 2f),
            scale(binding.tvI, 0, 1f, 2f),
            scale(binding.tvC, 0, 1f, 2f),
            scale(binding.tvE, 0, 1f, 2f),
            scale(binding.tvD, 0, 1f, 2f),
            scale(binding.tvW, 0, 1f, 2f),
            scale(binding.tvO, 0, 1f, 2f),
            scale(binding.tvR, 0, 1f, 2f),
            scale(binding.tvK, 0, 1f, 2f),
        )
    }

    return partTwo
}

private fun partThree(binding: FragmentGreetingsBinding): AnimatorSet {
    val partThree = AnimatorSet()

    partThree.run {
        playTogether(
            translateY(
                binding.ivLogo,
                0,
                0f,
                ((binding.layoutRoot.height - binding.ivLogo.height) / 2f) - (binding.ivLogo.height / 2)
            ),
            translateY(
                binding.tvS,
                0,
                0f,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f
            ),
            translateX(
                binding.tvS,
                0,
                0f,
                -130f
            ),
            translateY(
                binding.tvL,
                0,
                0f,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f
            ),
            translateX(
                binding.tvL,
                0,
                0f,
                -110f
            ),
            translateY(
                binding.tvI,
                0,
                0f,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f
            ),
            translateX(
                binding.tvI,
                0,
                0f,
                -100f
            ),
            translateY(
                binding.tvC,
                0,
                0f,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f
            ),
            translateX(
                binding.tvC,
                0,
                0f,
                -80f
            ),
            translateY(
                binding.tvE,
                0,
                0f,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f
            ),
            translateX(
                binding.tvE,
                0,
                0f,
                -50f
            ),
            translateY(
                binding.tvD,
                0,
                0f,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f
            ),
            translateX(
                binding.tvD,
                0,
                0f,
                -20f
            ),
            translateY(
                binding.tvW,
                0,
                0f,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f
            ),
            translateX(
                binding.tvW,
                0,
                0f,
                20f
            ),
            translateY(
                binding.tvO,
                0,
                0f,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f
            ),
            translateX(
                binding.tvO,
                0,
                0f,
                50f
            ),
            translateY(
                binding.tvR,
                0,
                0f,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f
            ),
            translateX(
                binding.tvR,
                0,
                0f,
                80f
            ),
            translateY(
                binding.tvK,
                0,
                0f,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f
            ),
            translateX(
                binding.tvK,
                0,
                0f,
                110f
            ),
        )
    }

    return partThree
}

private fun partFour(binding: FragmentGreetingsBinding): AnimatorSet {
    val partFour = AnimatorSet()

    partFour.run {
        playSequentially(
            fade(binding.tvS, 1000, 0f, 1f),
            fade(binding.tvW, 1000, 0f, 1f),
        )

    }

    return partFour
}

private fun partFive(binding: FragmentGreetingsBinding): AnimatorSet {
    val partFive = AnimatorSet()

    partFive.run {
        playTogether(
            fade(binding.ivLogo, 1000, 0f, 1f),
            fade(binding.tvL, 1000, 0f, 1f),
            fade(binding.tvI, 1000, 0f, 1f),
            fade(binding.tvC, 1000, 0f, 1f),
            fade(binding.tvE, 1000, 0f, 1f),
            fade(binding.tvD, 1000, 0f, 1f),
            fade(binding.tvO, 1000, 0f, 1f),
            fade(binding.tvR, 1000, 0f, 1f),
            fade(binding.tvK, 1000, 0f, 1f)
        )
    }

    return partFive
}

private fun partSix(binding: FragmentGreetingsBinding): AnimatorSet {
    val partSix = AnimatorSet()

    partSix.run {
        playTogether(
            translateY(
                binding.ivLogo,
                500,
                ((binding.layoutRoot.height - binding.ivLogo.height) / 2f) - (binding.ivLogo.height / 2),
                0f
            ),
            translateY(
                binding.tvS,
                500,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f,
                0f
            ),
            translateY(
                binding.tvL,
                500,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f,
                0f
            ),
            translateY(
                binding.tvI,
                500,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f,
                0f
            ),
            translateY(
                binding.tvC,
                500,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f,
                0f
            ),
            translateY(
                binding.tvE,
                500,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f,
                0f
            ),
            translateY(
                binding.tvD,
                500,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f,
                0f
            ),
            translateY(
                binding.tvW,
                500,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f,
                0f
            ),
            translateY(
                binding.tvO,
                500,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f,
                0f
            ),
            translateY(
                binding.tvR,
                500,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f,
                0f
            ),
            translateY(
                binding.tvK,
                500,
                (binding.layoutRoot.height - binding.ivLogo.height) / 2f,
                0f
            ),
            translateY(
                binding.ivLogo,
                0,
                0f,
                ((binding.layoutRoot.height - binding.ivLogo.height) / 2f) - (binding.ivLogo.height / 2)
            ),
            translateX(binding.tvS, 500, -130f, 0f),
            translateX(binding.tvL, 500, -110f, 0f),
            translateX(binding.tvI, 500, -100f, 0f),
            translateX(binding.tvC, 500, -80f, 0f),
            translateX(binding.tvE, 500, -50f, 0f),
            translateX(binding.tvD, 500, -20f, 0f),
            translateX(binding.tvW, 500, 20f, 0f),
            translateX(binding.tvO, 500, 50f, 0f),
            translateX(binding.tvR, 500, 80f, 0f),
            translateX(binding.tvK, 500, 110f, 0f),

            scale(binding.ivLogo, 500, 2f, 1f),
            scale(binding.tvS, 500, 2f, 1f),
            scale(binding.tvL, 500, 2f, 1f),
            scale(binding.tvI, 500, 2f, 1f),
            scale(binding.tvC, 500, 2f, 1f),
            scale(binding.tvE, 500, 2f, 1f),
            scale(binding.tvD, 500, 2f, 1f),
            scale(binding.tvW, 500, 2f, 1f),
            scale(binding.tvO, 500, 2f, 1f),
            scale(binding.tvR, 500, 2f, 1f),
            scale(binding.tvK, 500, 2f, 1f)
        )
    }

    return partSix
}

private fun partSeven(binding: FragmentGreetingsBinding): AnimatorSet {
    val partSeven = AnimatorSet()

    partSeven.run {
        playTogether(
            fade(binding.tvTitle, 500, 0f, 1f),
        )
    }

    return partSeven
}

private fun partEight(binding: FragmentGreetingsBinding): AnimatorSet {
    val partSeven = AnimatorSet()

    partSeven.run {
        playTogether(
            fade(binding.ivBackground, 500, 0f, 0.2f),
            fade(binding.tvSubtitle, 500, 0f, 1f),
            fade(binding.btnLogin, 500, 0f, 1f),
            fade(binding.btnRegister, 500, 0f, 1f),
        )
    }

    return partSeven
}

fun start(binding: FragmentGreetingsBinding) {
    AnimatorSet().run {
        playSequentially(
            partOne(binding),
            partTwo(binding),
            partThree(binding),
            partFour(binding),
            partFive(binding),
            partSix(binding),
            partSeven(binding),
            partEight(binding)
        )
        start()
    }
}
