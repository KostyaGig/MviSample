package ru.zinoviewk.mvi.ui.features.home

import ru.zinoviewk.mvi.domain.HomeResult

fun HomeResult.reduce(oldState: HomeState) : HomeState {
    return when(this) {
        is HomeResult.Progress -> HomeState.Progress(this.progress, oldState.saveSaveToCache)
        is HomeResult.Success -> HomeState.Success(this.data, oldState.saveSaveToCache)
        is HomeResult.DeleteSuccess -> HomeState.DeleteSuccess(this.message, oldState.saveSaveToCache)
        is HomeResult.Error -> HomeState.Error(this.message, oldState.saveSaveToCache)
    }
}