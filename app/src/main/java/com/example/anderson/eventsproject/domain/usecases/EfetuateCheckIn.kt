package com.example.anderson.eventsproject.domain.usecases

import com.example.anderson.eventsproject.domain.model.CheckIn
import com.example.anderson.eventsproject.domain.repo.IEventRepo
import javax.inject.Inject

class EfetuateCheckIn @Inject constructor(private val repo: IEventRepo) {
    suspend fun execute(checkIn: CheckIn) = repo.checkIn(checkIn)
}