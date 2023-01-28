package ru.zinoviewk.mvi.domain

sealed class HomeResult {

    class Progress(val progress: Int) : HomeResult()
    class Success(val data: String) : HomeResult()
    class DeleteSuccess(val message: String) : HomeResult()
    class Error(val message: String) : HomeResult()
}
