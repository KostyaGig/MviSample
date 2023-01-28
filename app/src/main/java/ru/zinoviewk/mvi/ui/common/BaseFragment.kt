package ru.zinoviewk.mvi.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter.ViewBinder
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding

typealias Inflater<B> = (LayoutInflater, ViewGroup?, Boolean) -> B

abstract class BaseFragment<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState, VM : BaseViewModel<STATE, INTENT, ACTION>, B : ViewBinding>(
    private val inflater: Inflater<B>,
    private val vmClass: Class<VM>
) : Fragment(), IViewRender<STATE> {

    private var _binding: B? = null
    protected val binding by lazy { checkNotNull(_binding) }

    abstract fun factory(): ViewModelProvider.Factory
    private val viewModel: VM by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, factory())[vmClass]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect(::render)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun dispatchIntent(intent: INTENT) = viewModel.dispatchIntent(intent)
}