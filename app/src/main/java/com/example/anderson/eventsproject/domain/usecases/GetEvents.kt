package com.example.anderson.eventsproject.domain.usecases

import com.example.anderson.eventsproject.domain.repo.IEventRepo
import javax.inject.Inject

class GetEvents @Inject constructor(private val repo: IEventRepo) {
    suspend fun execute() = repo.getEvents()
}