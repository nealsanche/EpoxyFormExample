package dev.nosuch.epoxyformexample

import com.airbnb.mvrx.MvRxState

data class FormState(
    val text: String = "",
    val textValid: Boolean = false,
    val unvalidatedText: String = "",
    val unvalidatedText2: String = "",
    val unvalidatedText3: String = ""
) : MvRxState {
    val enableActionButton = textValid
            && unvalidatedText.isNotBlank()
            && unvalidatedText2.isNotBlank()
            && unvalidatedText3.isNotBlank()
}