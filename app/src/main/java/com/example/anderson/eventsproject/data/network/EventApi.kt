package com.example.anderson.eventsproject.data.network

import com.example.anderson.eventsproject.data.model.CheckInRequest
import com.example.anderson.eventsproject.data.model.CheckInResponse
import com.example.anderson.eventsproject.data.model.EventsResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface EventApi {
    @GET("events")
    suspend fun getEvents(): Response<List<EventsResponse>>

    @GET("events/{id}")
    suspend fun getDetailEvent(@Path("id") id: Int): Response<EventsResponse>

    @POST("/checkin")
    suspend fun checkIn(@Body request: CheckInRequest): Response<CheckInResponse>
}