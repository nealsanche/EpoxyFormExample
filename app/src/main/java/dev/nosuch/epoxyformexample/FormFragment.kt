package dev.nosuch.epoxyformexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import kotlinx.android.synthetic.main.fragment_form.*

/**
 * A simple [Fragment] subclass.
 */
class FormFragment : BaseMvRxFragment() {

    private val viewModel: FormViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun invalidate() = withState(viewModel) { state ->
        epoxy_recycler_view.withModels {

            inputText {
                id("text")
                value(state.text)
                textWatcher(makeTextWatcher {
                    viewModel.onText(it)
                })
            }
        }
    }

}
