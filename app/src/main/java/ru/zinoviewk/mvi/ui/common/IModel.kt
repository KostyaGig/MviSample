package ru.zinoviewk.mvi.ui.common

import kotlinx.coroutines.flow.StateFlow

interface IModel<STATE : ViewState, INTENT : ViewIntent> {

    val state: StateFlow<STATE>

    fun dispatchIntent(intent: INTENT)
}