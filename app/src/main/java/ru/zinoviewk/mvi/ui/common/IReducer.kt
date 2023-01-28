package ru.zinoviewk.mvi.ui.common

interface IReducer<T, STATE> : ViewState {

    fun reduce(data: T, state: STATE) : STATE
}