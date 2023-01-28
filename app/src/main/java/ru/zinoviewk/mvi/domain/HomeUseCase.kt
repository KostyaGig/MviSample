package ru.zinoviewk.mvi.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.zinoviewk.mvi.data.HomeRepository

interface HomeUseCase {

    suspend fun loadDefaultData(): Flow<HomeResult>

    suspend fun updateData(newData: String): Flow<HomeResult>

    suspend fun deleteData(): Flow<HomeResult>
}

class HomeUseCaseImpl(
    private val repository: HomeRepository
) : HomeUseCase {
    override suspend fun loadDefaultData(): Flow<HomeResult> = flow {
        progress(30) { progress ->
            emit(HomeResult.Progress(progress))
        }
        val data = repository.fetchDefaultData()
        emit(HomeResult.Success("Data was Loaded! $data"))
    }

    override suspend fun updateData(newData: String): Flow<HomeResult> = flow {
        if(newData.isEmpty())
            emit(HomeResult.Error("Data cannot be empty"))
        else {
            progress(60) { progress ->
                emit(HomeResult.Progress(progress))
            }
            emit(HomeResult.Success("Data was Updated! $newData"))
        }

    }

    override suspend fun deleteData(): Flow<HomeResult> = flow {
        progress(10) { progress ->
            emit(HomeResult.Progress(progress))
        }
        emit(HomeResult.DeleteSuccess("Data was Deleted!"))
    }

    private suspend fun progress(delay: Long, block: suspend (Int) -> Unit) {
        (1..100).forEach { progress ->
            delay(delay)
            block(progress)
        }
    }

}

