package com.example.anderson.eventsproject.app.features.eventdetail.viewmodel

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anderson.eventsproject.R
import com.example.anderson.eventsproject.app.util.ConnectionTest
import com.example.anderson.eventsproject.domain.model.CheckIn
import com.example.anderson.eventsproject.domain.model.Event
import com.example.anderson.eventsproject.domain.usecases.EfetuateCheckIn
import com.example.anderson.eventsproject.domain.usecases.GetEventDetails
import com.example.anderson.eventsproject.domain.usecases.ValidateEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventDetailViewModel @Inject constructor( var context: Context) : ViewModel() {

    private val _event = MutableLiveData<Event>()
    var eventDetail: LiveData<Event> = _event

    private val _nameMessageError = MutableLiveData<String>()
    var nameMessageError: LiveData<String> = _nameMessageError

    private val _emailMessageError = MutableLiveData<String>()
    var emailMessageError: LiveData<String> = _emailMessageError

    private val _formCheckInValid = MutableLiveData<Boolean>()
    var formCheckInValid: LiveData<Boolean> = _formCheckInValid

    private val _checkInSucess = MutableLiveData<Boolean>()
    var checkInSucess: LiveData<Boolean> = _checkInSucess

    private val _isHasInternet= MutableLiveData<Boolean>()
    var isHasInternet: LiveData<Boolean> = _isHasInternet

    var isNameValid = false
    var isEmailValid = false

    @Inject
    lateinit var getEventDetails: GetEventDetails

    @Inject
    lateinit var efetuateCheckIn: EfetuateCheckIn

    @Inject
    lateinit var validateEmail: ValidateEmail

    fun getEventDetails(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (ConnectionTest.verifyConectation()) {
                    _isHasInternet.postValue(true)
                    val eventDetails = getEventDetails.execute(id)
                    if (eventDetails != null) {
                        _event.postValue(eventDetails)
                        Log.d("Detail", eventDetails.toString())
                    }
                } else {
                    _isHasInternet.postValue(false)
                }

            }
        }
    }

    fun checkIn(checkIn: CheckIn) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (ConnectionTest.verifyConectation()) {
                    _isHasInternet.postValue(true)
                    _checkInSucess.postValue(efetuateCheckIn.execute(checkIn))
                }else {
                    _isHasInternet.postValue(false)
                }
            }
        }
    }


    fun nameData(userName: String) {
        if (userNameValid(userName)) {
            isNameValid = true
            _nameMessageError.value = ""
        } else {
            _nameMessageError.value = context.getString(R.string.name_invalid);
            isNameValid = false
        }
        formCheckInValid()
    }

    fun emailData(email: String){
        if(validateEmail.execute(email)){
            isEmailValid = true
            _emailMessageError.value = ""
        }else {
            isEmailValid = false
            _emailMessageError.value = context.getString(R.string.email_invalid)
        }
        formCheckInValid()
    }

    private fun emailValid(email: String) : Boolean{
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }


    private fun formCheckInValid(){
        _formCheckInValid.value =  isNameValid && isEmailValid
    }

    private fun userNameValid(userName: String): Boolean {
        if (userName.length > 3) {
            return true
        }
        return false
    }

}