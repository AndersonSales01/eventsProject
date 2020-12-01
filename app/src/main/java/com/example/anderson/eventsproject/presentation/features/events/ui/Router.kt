package com.example.anderson.eventsproject.presentation.features.events.ui

import com.example.anderson.eventsproject.domain.model.Event

interface Router {
    fun toGoDetailScreen(event: Event)
}