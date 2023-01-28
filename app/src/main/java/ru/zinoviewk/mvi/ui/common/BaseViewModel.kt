package ru.zinoviewk.mvi.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : ViewState, INTENT : ViewIntent, ACTION : ViewAction>(
    private val defaultState: STATE
) : IModel<STATE, INTENT>, ViewModel() {

    protected val _state = MutableStateFlow(defaultState)
    override val state: StateFlow<STATE> = _state

    final override fun dispatchIntent(intent: INTENT) {
        handleAction(intentToAction(intent))
    }

    abstract fun intentToAction(intent: INTENT): ACTION
    abstract fun handleAction(action: ACTION)

    protected fun launchOnUi(block: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) { block() }
    }
}