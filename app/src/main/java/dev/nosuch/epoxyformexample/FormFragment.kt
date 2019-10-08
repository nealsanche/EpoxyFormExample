package dev.nosuch.epoxyformexample

import android.widget.Toast
import com.airbnb.mvrx.fragmentViewModel
import dev.nosuch.epoxyformexample.common.EpoxyBaseFragment
import dev.nosuch.epoxyformexample.common.simpleController
import timber.log.Timber

class FormFragment : EpoxyBaseFragment() {

    private val viewModel: FormViewModel by fragmentViewModel()

    // Override to disable async model building
    override val enableAsyncEpoxyModelBuilding: Boolean
        get() = false

    override fun epoxyController() = simpleController(viewModel) { state ->

        Timber.i("Building Models: ${Thread.currentThread().name}")

        inputText {
            id("text")
            value(state.text)
            hint("Enter some text")

            onBind { _, view, _ ->
                FormHelper.bind(view) { validator ->
                    makeTextWatcher {
                        modelBuildDelay = 1000 // Also causes action button to remain disabled too long
                        viewModel.onText(it, validator.validate())
                    }
                }
            }

            onUnbind { _, view ->
                FormHelper.unbind(view)
            }
        }

        inputText {
            id("unvalidated")
            value(state.unvalidatedText)
            hint("Enter some unvalidated text")

            textWatcher(makeTextWatcher {
                viewModel.onUnvalidatedText(it)
            })
        }

        inputText {
            id("unvalidated2")
            value(state.unvalidatedText2)
            hint("Enter some unvalidated text")

            textWatcher(makeTextWatcher {
                viewModel.onUnvalidated2Text(it)
            })
        }

        inputText {
            id("unvalidated3")
            value(state.unvalidatedText3)
            hint("Enter some unvalidated text")

            textWatcher(makeTextWatcher {
                viewModel.onUnvalidated3Text(it)
            })
        }

        actionRow {
            id("action")
            title("Click Me")
            enabled(state.enableActionButton)
            onActionClicked { _ ->
                Toast.makeText(requireContext(), "Action Button Clicked", Toast.LENGTH_LONG).show()
            }
        }
    }

}
