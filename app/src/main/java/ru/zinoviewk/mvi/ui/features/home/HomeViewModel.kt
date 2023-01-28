package ru.zinoviewk.mvi.ui.features.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOn
import ru.zinoviewk.mvi.domain.HomeResult
import ru.zinoviewk.mvi.domain.HomeUseCase
import ru.zinoviewk.mvi.ui.common.BaseViewModel

class HomeViewModel(
    private val useCase: HomeUseCase
) : BaseViewModel<HomeState, HomeIntent, HomeAction>(HomeState.Empty) {

    init {
        dispatchIntent(HomeIntent.LoadDefaultHomeData)
    }

    override fun intentToAction(intent: HomeIntent): HomeAction {
        return when (intent) {
            is HomeIntent.LoadDefaultHomeData -> HomeAction.LoadDefaultData
            is HomeIntent.UpdateHomeData -> HomeAction.UpdateData(intent.newData)
            is HomeIntent.DeleteHomeData -> HomeAction.DeleteData
        }
    }

    override fun handleAction(action: HomeAction) {
        viewModelScope.launch {
            when (action) {
                is HomeAction.LoadDefaultData -> {
                    useCase
                        .loadDefaultData()
                        .flowOn(Dispatchers.IO)
                        .collect(::emitState)
                }
                is HomeAction.UpdateData -> useCase
                    .updateData(action.newData)
                    .collect(::emitState)
                is HomeAction.DeleteData -> useCase.deleteData().collect(::emitState)
            }
        }
    }

    private fun emitState(result: HomeResult) {
        _state.value = result.reduce()
    }
}