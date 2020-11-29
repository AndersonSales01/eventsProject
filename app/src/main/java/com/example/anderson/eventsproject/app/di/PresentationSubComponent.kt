package com.example.anderson.eventsproject.app.di

import com.example.anderson.eventsproject.app.features.eventdetail.ui.EventDetailActivity
import com.example.anderson.eventsproject.app.features.events.ui.EventsActivity
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