package com.example.anderson.eventsproject.domain.repo

import com.example.anderson.eventsproject.domain.model.Event

interface IEventRepo {
  suspend fun getEvents() : List<Event>
}