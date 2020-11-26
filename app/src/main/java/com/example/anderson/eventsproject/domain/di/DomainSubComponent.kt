package com.example.anderson.eventsproject.domain.di

import dagger.Subcomponent


@Subcomponent(modules = [UseCasesModule::class])
interface DomainSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DomainSubComponent
    }
}