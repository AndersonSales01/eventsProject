package com.example.anderson.eventsproject.app.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anderson.eventsproject.R

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    abstract fun observables()
    abstract fun setupUI()
    abstract fun initialize()
}