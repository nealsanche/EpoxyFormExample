package dev.nosuch.epoxyformexample

import com.airbnb.mvrx.MvRxState

data class FormState(
    val text: CharSequence = ""
) : MvRxState