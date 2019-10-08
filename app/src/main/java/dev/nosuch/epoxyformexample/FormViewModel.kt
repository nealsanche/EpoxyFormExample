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

    fun onUnvalidatedText(text: CharSequence) {
        setState {
            copy(unvalidatedText = text.toString())
        }
    }

    fun onUnvalidated2Text(text: CharSequence) {
        setState {
            copy(unvalidatedText2 = text.toString())
        }
    }

    fun onUnvalidated3Text(text: CharSequence) {
        setState {
            copy(unvalidatedText3 = text.toString())
        }
    }

    companion object : MvRxViewModelFactory<FormViewModel, FormState> {
        override fun create(viewModelContext: ViewModelContext, state: FormState): FormViewModel? =
            FormViewModel(
                initialState = state
            )

    }
}