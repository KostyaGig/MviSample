package ru.zinoviewk.mvi.ui.features.home

import ru.zinoviewk.mvi.ui.common.ViewIntent

sealed class HomeIntent : ViewIntent {

    object LoadDefaultHomeData : HomeIntent()
    class UpdateHomeData(val newData: String) : HomeIntent()
    class SaveToCache(val isChecked: Boolean) : HomeIntent()
    object DeleteHomeData : HomeIntent()
}