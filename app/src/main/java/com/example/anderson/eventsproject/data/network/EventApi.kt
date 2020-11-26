package com.example.anderson.eventsproject.data.network

import com.example.anderson.eventsproject.data.model.EventsResponse
import retrofit2.Response
import retrofit2.http.GET

interface EventApi {
    @GET("events")
    suspend fun getEvents(): Response<List<EventsResponse>>
}