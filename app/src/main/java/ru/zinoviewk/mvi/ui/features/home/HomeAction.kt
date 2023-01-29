package ru.zinoviewk.mvi.ui.features.home

import ru.zinoviewk.mvi.ui.common.ViewAction

sealed class HomeAction : ViewAction {

    object LoadDefaultData : HomeAction()
    class UpdateData(val newData: String) : HomeAction()
    class SaveToCache(val isChecked: Boolean) : HomeAction()
    object DeleteData : HomeAction()
}