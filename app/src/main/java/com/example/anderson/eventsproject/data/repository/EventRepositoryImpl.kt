package com.example.anderson.eventsproject.data.repository

import com.example.anderson.eventsproject.data.mappers.EventMapper
import com.example.anderson.eventsproject.data.network.EventApi
import com.example.anderson.eventsproject.data.network.RetrofitConfig
import com.example.anderson.eventsproject.domain.model.Event
import com.example.anderson.eventsproject.domain.repo.IEventRepo
import java.lang.Exception
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


}