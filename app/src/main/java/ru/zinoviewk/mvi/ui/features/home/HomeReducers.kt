package ru.zinoviewk.mvi.ui.features.home

import ru.zinoviewk.mvi.domain.HomeResult

fun HomeResult.reduce() : HomeState {
    return when(this) {
        is HomeResult.Progress -> HomeState.Progress(this.progress)
        is HomeResult.Success -> HomeState.Success(this.data)
        is HomeResult.DeleteSuccess -> HomeState.DeleteSuccess(this.message)
        is HomeResult.Error -> HomeState.Error(this.message)
    }
}