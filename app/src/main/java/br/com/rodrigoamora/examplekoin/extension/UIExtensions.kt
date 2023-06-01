package br.com.rodrigoamora.examplekoin.extension

import android.widget.EditText

fun EditText.checkText(errorMessage: String): Boolean {
    if (text.toString().isEmpty()) {
        error = errorMessage
        return false
    }
    return true
}