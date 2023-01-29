package ru.zinoviewk.mvi.ui.features.home

import ru.zinoviewk.mvi.ui.common.ViewState

sealed class HomeState : ViewState {

    abstract val saveSaveToCache: SaveToCache
    abstract fun copy(isSaveToCache: Boolean) : HomeState

    data class Empty(
        override val saveSaveToCache: SaveToCache = SaveToCache(isChecked = false)
    ) : HomeState() {

        override fun copy(isSaveToCache: Boolean): HomeState = copy(saveSaveToCache = SaveToCache(isSaveToCache))
    }

    data class Progress(
        val progress: Int,
        override val saveSaveToCache: SaveToCache = SaveToCache(isChecked = false)
    ) : HomeState() {

        override fun copy(isSaveToCache: Boolean): HomeState = copy(saveSaveToCache = SaveToCache(isSaveToCache))
    }

    data class Success(
        val data: String,
        override val saveSaveToCache: SaveToCache = SaveToCache(isChecked = false)
    ) : HomeState() {

        override fun copy(isSaveToCache: Boolean): HomeState = copy(saveSaveToCache = SaveToCache(isSaveToCache))
    }

    data class DeleteSuccess(
        val message: String,
        override val saveSaveToCache: SaveToCache = SaveToCache(isChecked = false)
    ) : HomeState() {

        override fun copy(isSaveToCache: Boolean): HomeState = copy(saveSaveToCache = SaveToCache(isSaveToCache))
    }

    data class Error(
        val message: String,
        override val saveSaveToCache: SaveToCache = SaveToCache(isChecked = false)
    ) : HomeState() {

        override fun copy(isSaveToCache: Boolean): HomeState = copy(saveSaveToCache = SaveToCache(isSaveToCache))
    }

    data class SaveToCache(val isChecked: Boolean)
}