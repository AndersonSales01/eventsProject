package com.example.anderson.eventsproject.data.model

import com.google.gson.annotations.SerializedName

data class EventsResponse (
    @SerializedName("date")
    var date: Long,
    @SerializedName("description")
    var description: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("longitude")
    var longitude: String,
    @SerializedName("latitude")
    var latitude: String,
    @SerializedName("price")
    var price: Float,
    @SerializedName("title")
    var title: String,
    @SerializedName("id")
    var id: Int
)