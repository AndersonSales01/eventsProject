package com.example.anderson.eventsproject.di

import android.content.Context
import com.example.anderson.eventsproject.app.di.PresentationSubComponent
import com.example.anderson.eventsproject.domain.di.DomainSubComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [SubcomponentsModule::class, RepositoryModule::class,NetWorkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun presentationSubComponent(): PresentationSubComponent.Factory
}

@Module(
    subcomponents = [
      //  DataSubComponent::class,
        PresentationSubComponent::class,
        DomainSubComponent::class
    ]
)
object SubcomponentsModule