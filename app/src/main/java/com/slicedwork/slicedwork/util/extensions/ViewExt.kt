package com.slicedwork.slicedwork.util.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.focusAndShowSoftKeyboard(context: Context) {
    this.requestFocus()
    val inputMethodManager: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun View.hideKeyBoard(context: Context) {
    val inputMethodManager: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

