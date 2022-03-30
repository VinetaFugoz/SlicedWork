package com.slicedwork.slicedwork.presentation.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object ViewExt {
    fun View.focusAndShowSoftKeyboard(context: Context) {
        this.requestFocus()
        val inputMethodManager: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}