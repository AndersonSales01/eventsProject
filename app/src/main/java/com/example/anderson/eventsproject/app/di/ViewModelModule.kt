package com.example.anderson.eventsproject.app.di

import androidx.lifecycle.ViewModel
import com.example.anderson.eventsproject.app.features.eventdetail.viewmodel.EventDetailViewModel
import com.example.anderson.eventsproject.app.features.events.viewmodel.EventsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    abstract fun bindEventViewModel(viewmodel: EventsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventDetailViewModel::class)
    abstract fun bindEventDetailsViewModel(viewmodel: EventDetailViewModel): ViewModel

}