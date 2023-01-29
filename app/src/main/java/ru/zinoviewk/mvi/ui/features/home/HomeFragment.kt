package ru.zinoviewk.mvi.ui.features.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import ru.zinoviewk.mvi.data.HomeRepositoryImpl
import ru.zinoviewk.mvi.databinding.HomeFragmentBinding
import ru.zinoviewk.mvi.domain.HomeUseCaseImpl
import ru.zinoviewk.mvi.ui.common.BaseFragment

class HomeFragment :
    BaseFragment<HomeIntent, HomeAction, HomeState, HomeViewModel, HomeFragmentBinding>(
        HomeFragmentBinding::inflate,
        HomeViewModel::class.java
    ) {

    override fun factory(): ViewModelProvider.Factory = HomeViewModeFactory(
        HomeUseCaseImpl(
            HomeRepositoryImpl()
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.updateBtn.setOnClickListener {
            dispatchIntent(HomeIntent.UpdateHomeData(binding.dataField.text.toString().trim()))
        }

        binding.deleteBtn.setOnClickListener {
            dispatchIntent(HomeIntent.DeleteHomeData)
        }

        binding.saveToCacheChb.setOnCheckedChangeListener { _, isChecked ->
            dispatchIntent(HomeIntent.SaveToCache(isChecked))
        }

    }

    override fun render(state: HomeState) {
        binding.progress.isVisible = state is HomeState.Progress
        binding.resultTv.isVisible = state is HomeState.Success || state is HomeState.DeleteSuccess
        binding.errorTv.isVisible = state is HomeState.Error

        when (state) {
            is HomeState.Progress -> binding.progress.progress = state.progress
            is HomeState.Success -> binding.resultTv.text = state.data
            is HomeState.DeleteSuccess -> {
                binding.dataField.setText("")
                binding.resultTv.text = state.message
            }
            is HomeState.Error -> binding.errorTv.text = state.message
            else -> Unit
        }
    }

}