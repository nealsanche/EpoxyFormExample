package dev.nosuch.epoxyformexample

import android.text.TextWatcher
import androidx.databinding.ViewDataBinding
import br.com.ilhasoft.support.validation.Validator
import com.airbnb.epoxy.DataBindingEpoxyModel

/**
 * Helps ensure that TextWatchers and validators are assigned only once per view binding, to
 * cut down on the number of objects created during Epoxy form updates.
 *
 * Example code is shown below for how this might be used.
 * Note: Ensure that the app:inputText property is set on the EditText to prevent issues with
 * cursor jumping when the model state is updated.
 */

/*
inputUsername {
    id("username")
    hint("Username")
    state.userName.let { value(it) }
    setFilterDuplicates(true)

    onBind { _, view, _ ->
        FormHelper.bind(view) { validator ->
            makeTextWatcher {
                epoxyController.requestDelayedModelBuild(REBUILD_DELAY)
                viewModel.onUsername(it, validator.validate())
            }
        }
    }

    onUnbind { _, view ->
        FormHelper.unbind(view)
    }
}
 */
object FormHelper {
    private val formFields = mutableMapOf<ViewDataBinding, Validator>()

    fun bind(holder: DataBindingEpoxyModel.DataBindingHolder, watchMaker: (Validator) -> TextWatcher) {
        if (formFields.containsKey(holder.dataBinding)) return

        val validator = Validator(holder.dataBinding)
        holder.dataBinding.setVariable(BR.textWatcher, watchMaker(validator))

        formFields[holder.dataBinding] = validator
    }

    fun unbind(holder: DataBindingEpoxyModel.DataBindingHolder) {
        formFields.remove(holder.dataBinding)
        holder.dataBinding.setVariable(BR.textWatcher, null)
    }
}
