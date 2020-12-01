package com.example.anderson.eventsproject.presentation.di

import com.example.anderson.eventsproject.presentation.features.eventdetail.ui.EventDetailActivity
import com.example.anderson.eventsproject.presentation.features.events.ui.EventsActivity
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelBuilderModule::class,ViewModelModule::class])
interface PresentationSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PresentationSubComponent
    }

    fun inject(activity: EventsActivity)
    fun inject(activity: EventDetailActivity)
}