package com.xoxoer.gitpocket.util.common

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.dismissKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}