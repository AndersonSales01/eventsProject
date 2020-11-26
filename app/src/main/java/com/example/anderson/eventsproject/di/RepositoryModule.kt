package com.example.anderson.eventsproject.di

import com.example.anderson.eventsproject.data.repository.EventRepositoryImpl
import com.example.anderson.eventsproject.domain.repo.IEventRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindEventRepository(repositoryImpl: EventRepositoryImpl): IEventRepo
}