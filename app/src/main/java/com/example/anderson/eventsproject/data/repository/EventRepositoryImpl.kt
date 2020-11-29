package com.example.anderson.eventsproject.data.repository

import com.example.anderson.eventsproject.data.mappers.EventMapper
import com.example.anderson.eventsproject.data.model.CheckInRequest
import com.example.anderson.eventsproject.data.network.EventApi
import com.example.anderson.eventsproject.domain.model.CheckIn
import com.example.anderson.eventsproject.domain.model.Event
import com.example.anderson.eventsproject.domain.repo.IEventRepo
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(private var endPoint: EventApi) : IEventRepo {
    override suspend fun getEvents(): List<Event> {

        val listEvents: ArrayList<Event> = ArrayList()

        val response = endPoint.getEvents()

        if (response.isSuccessful) {

            response.let {
                if (response.body()!!.isNotEmpty()) {

                    for (item in response.body()!!) {

                        val eventObject = EventMapper.toEventObject(item)

                        listEvents.add(eventObject)
                    }
                }
            }
        }
        return listEvents
    }

    override suspend fun getEventDetail(id: Int): Event? {
        var event: Event? = null
        val response = endPoint.getDetailEvent(id)
        if (response.isSuccessful) {
            response.let {
                event = EventMapper.toEventObject(response.body()!!)
            }
        }
        return event
    }

    override suspend fun checkIn(checkIn: CheckIn): Boolean {
        var success = false
        val checkInRequest = CheckInRequest(checkIn.name, checkIn.email, checkIn.idTest)
        val response = endPoint.checkIn(checkInRequest)
        if (response.isSuccessful) {
            success = response.body()?.code == "200"
        }
        return success
    }


}