package com.redspot.kotlinpractice.framework.ui

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.showSnackbar(
    message: String,
    actionText: String,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar
        .make(this, message, length)
        .setAction(actionText, action)
        .show()
}

fun View.show() {
    if (this.visibility != View.VISIBLE) {
        this.visibility = View.VISIBLE
    }
}

fun View.hide() {
    if (this.visibility != View.GONE) {
        this.visibility = View.GONE
    }
}