package com.example.anderson.eventsproject

import android.app.Application
import com.example.anderson.eventsproject.di.AppComponent
import com.example.anderson.eventsproject.di.DaggerAppComponent

class MyApplication : Application() {
   lateinit var  appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}