package dev.nosuch.epoxyformexample

import android.text.Editable
import android.text.TextWatcher

inline fun makeTextWatcher(crossinline block: (CharSequence) -> Unit): TextWatcher =
    object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            block(s)
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }
    }
