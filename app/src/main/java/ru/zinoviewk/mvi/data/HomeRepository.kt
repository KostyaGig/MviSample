package ru.zinoviewk.mvi.data

interface HomeRepository {

    suspend fun fetchDefaultData() : String
}

class HomeRepositoryImpl : HomeRepository {
    private var count = 1

    override suspend fun fetchDefaultData() = count.toString()
}


