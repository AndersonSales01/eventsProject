package com.example.anderson.eventsproject.domain.di

import com.example.anderson.eventsproject.domain.repo.IEventRepo
import com.example.anderson.eventsproject.domain.usecases.GetEvents
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Provides
    @Singleton
    fun providerGetEvents(repository: IEventRepo) =  GetEvents(repository)
}