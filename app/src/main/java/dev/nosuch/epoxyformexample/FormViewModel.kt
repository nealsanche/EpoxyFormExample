package dev.nosuch.epoxyformexample

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext

class FormViewModel(
    initialState: FormState
) : BaseMvRxViewModel<FormState>(initialState, debugMode = BuildConfig.DEBUG) {

    fun onText(text: CharSequence, valid: Boolean) {
        setState {
            copy(text = text.toString(), textValid = valid)
        }
    }

    companion object : MvRxViewModelFactory<FormViewModel, FormState> {
        override fun create(viewModelContext: ViewModelContext, state: FormState): FormViewModel? =
            FormViewModel(
                initialState = state
            )

    }
}