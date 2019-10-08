package dev.nosuch.epoxyformexample

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext

class FormViewModel(
    initialState: FormState
) : BaseMvRxViewModel<FormState>(initialState, debugMode = BuildConfig.DEBUG) {

    fun onText(text: CharSequence) {
        setState {
            copy(text = text)
        }
    }

    companion object : MvRxViewModelFactory<FormViewModel, FormState> {
        override fun create(viewModelContext: ViewModelContext, state: FormState): FormViewModel? =
            FormViewModel(
                initialState = state
            )

    }
}