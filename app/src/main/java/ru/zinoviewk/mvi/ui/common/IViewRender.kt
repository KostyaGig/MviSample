package ru.zinoviewk.mvi.ui.common

interface IViewRender<STATE : ViewState> {

    fun render(state: STATE)
}