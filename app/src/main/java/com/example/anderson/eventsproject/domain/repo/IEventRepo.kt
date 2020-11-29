package com.example.anderson.eventsproject.domain.repo

import com.example.anderson.eventsproject.domain.model.CheckIn
import com.example.anderson.eventsproject.domain.model.Event

interface IEventRepo {
  suspend fun getEvents() : List<Event>
  suspend fun getEventDetail( id: Int) : Event?
  suspend fun checkIn(checkIn: CheckIn) : Boolean
}