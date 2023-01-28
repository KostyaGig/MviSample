package ru.zinoviewk.mvi.ui.features.home

import ru.zinoviewk.mvi.ui.common.ViewState

sealed class HomeState : ViewState {

    object Empty : HomeState()

    class Progress(val progress: Int) : HomeState()

    class Success(val data: String) : HomeState()

    class DeleteSuccess(val message: String) : HomeState()

    class Error(val message: String) : HomeState()
}