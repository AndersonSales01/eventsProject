package com.example.anderson.eventsproject.domain.di

import com.example.anderson.eventsproject.domain.usecases.ValidateEmail
import com.example.anderson.eventsproject.domain.repo.IEventRepo
import com.example.anderson.eventsproject.domain.usecases.EfetuateCheckIn
import com.example.anderson.eventsproject.domain.usecases.GetEventDetails
import com.example.anderson.eventsproject.domain.usecases.GetEvents
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Provides
    @Singleton
    fun providerGetEvents(repository: IEventRepo) =  GetEvents(repository)

    @Provides
    @Singleton
    fun providerGetEventDetails(repository: IEventRepo) =  GetEventDetails(repository)

    @Provides
    @Singleton
    fun providerEfetuateCheckIn(repository: IEventRepo) =  EfetuateCheckIn(repository)

    @Provides
    @Singleton
    fun providerValidateEmail() =
        ValidateEmail()
}