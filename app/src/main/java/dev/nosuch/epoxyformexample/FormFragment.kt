package dev.nosuch.epoxyformexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import kotlinx.android.synthetic.main.fragment_form.*
import timber.log.Timber

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        epoxy_recycler_view.setRecycledViewPool(RecyclerView.RecycledViewPool())
    }

    override fun invalidate() = withState(viewModel) { state ->
        epoxy_recycler_view.withModels {

            Timber.i("Building Models: ${Thread.currentThread().name}")

            inputText {
                id("text")
                value(state.text)
                hint("Enter some text")

                onBind { _, view, _ ->
                    FormHelper.bind(view) {validator ->
                        makeTextWatcher {
                            viewModel.onText(it, validator.validate())
                        }
                    }
                }

                onUnbind { _, view ->
                    FormHelper.unbind(view)
                }
            }
        }
    }

}
