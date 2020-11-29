package com.example.anderson.eventsproject.app.features.events.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anderson.eventsproject.domain.model.Event
import com.example.anderson.eventsproject.domain.usecases.GetEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventsViewModel @Inject constructor() : ViewModel() {

    private val _listEvents = MutableLiveData<List<Event>>()
    var listEvent: LiveData<List<Event>> = _listEvents

    private val _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    @Inject
    lateinit var getEvents: GetEvents

    fun getEvents() {
        _loading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val listEvents = getEvents.execute()

                    if (listEvents.isNotEmpty()) {
                        _listEvents.postValue(listEvents)
                    }

                } catch (e: Exception) {

                }

                _loading.postValue(false)
            }
        }
    }
}