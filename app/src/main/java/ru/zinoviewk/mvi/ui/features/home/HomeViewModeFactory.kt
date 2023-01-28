package ru.zinoviewk.mvi.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.zinoviewk.mvi.domain.HomeUseCase

@Suppress("UNCHECKED_CAST")
class HomeViewModeFactory(
    private val useCase: HomeUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == HomeViewModel::class.java)
        return HomeViewModel(useCase) as T
    }
}