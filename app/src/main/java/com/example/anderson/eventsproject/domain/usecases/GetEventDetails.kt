package com.example.anderson.eventsproject.domain.usecases

import com.example.anderson.eventsproject.domain.model.Event
import com.example.anderson.eventsproject.domain.repo.IEventRepo
import javax.inject.Inject

class GetEventDetails @Inject constructor(private val repo: IEventRepo) {
    suspend fun execute(id: Int) =
        repo.getEventDetail(id)
}