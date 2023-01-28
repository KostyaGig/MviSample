package ru.zinoviewk.mvi.ui.features.home

import ru.zinoviewk.mvi.ui.common.ViewIntent

sealed class HomeIntent : ViewIntent {

    object LoadDefaultHomeData : HomeIntent()
    class UpdateHomeData(val newData: String) : HomeIntent()
    object DeleteHomeData : HomeIntent()
}