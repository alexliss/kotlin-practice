package com.redspot.kotlinpractice.framework.ui

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.createAndShow(
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