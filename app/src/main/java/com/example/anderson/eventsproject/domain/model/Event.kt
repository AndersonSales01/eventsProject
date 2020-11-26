package com.example.anderson.eventsproject.domain.model

data class Event(
    var date: Long,
    var description: String,
    var image: String,
    var longitude: String,
    var latitude: String,
    var price: Float,
    var title: String,
    var id: Int
)