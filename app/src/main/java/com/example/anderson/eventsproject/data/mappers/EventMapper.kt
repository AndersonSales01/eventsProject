package com.example.anderson.eventsproject.data.mappers

import com.example.anderson.eventsproject.data.model.EventsResponse
import com.example.anderson.eventsproject.domain.model.Event

object EventMapper {

    fun toEventObject(response: EventsResponse): Event {
        return Event(
            response.date,
            response.description,
            response.image,
            response.longitude,
            response.latitude,
            response.price,
            response.title,
            response.id
        )
    }
}