package dev.nosuch.epoxyformexample.common


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.BaseMvRxFragment
import dev.nosuch.epoxyformexample.R

/**
 * Influenced by Base Fragment in the MvRx Sample App
 *
 * https://github.com/airbnb/MvRx/tree/master/sample
 */
abstract class EpoxyBaseFragment : BaseMvRxFragment() {

    protected lateinit var recyclerView: EpoxyRecyclerView
    protected val epoxyController by lazy { epoxyController() }

    open val enableAsyncEpoxyModelBuilding: Boolean = true

    protected var modelBuildDelay: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        epoxyController.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_epoxy_base, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.epoxy_recycler_view)

        // Fix possible problems with spurious form validation
        recyclerView.setRecycledViewPool(RecyclerView.RecycledViewPool())

        recyclerView.setController(epoxyController)
        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).isEnabled = false
    }

    override fun invalidate() {
        modelBuildDelay?.let {
            epoxyController.requestDelayedModelBuild(it)
        } ?: epoxyController.requestModelBuild()
    }

    /**
     * Provide the EpoxyController to use when building models for this Fragment.
     * Basic usages can simply use [simpleController]
     */
    abstract fun epoxyController(): MvRxEpoxyController

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        epoxyController.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        epoxyController.cancelPendingModelBuild()
        super.onDestroyView()
    }
}
