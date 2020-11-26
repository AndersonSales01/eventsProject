package com.example.anderson.eventsproject.app.features.events.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anderson.eventsproject.MyApplication
import com.example.anderson.eventsproject.R
import com.example.anderson.eventsproject.app.features.BaseActivity
import com.example.anderson.eventsproject.app.features.events.viewmodel.EventsViewModel
import kotlinx.android.synthetic.main.activity_events.*
import javax.inject.Inject

class EventsActivity : BaseActivity() {

    private lateinit var viewModel: EventsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var manager: LinearLayoutManager
    private lateinit var adapter: EventsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        (applicationContext as MyApplication).appComponent.presentationSubComponent().create()
            .inject(this)

        initialize()
    }

    override fun initialize() {

        viewModel = ViewModelProvider(this, viewModelFactory).get(EventsViewModel::class.java)

        setupUI()

        observables()

        viewModel.getEvents()
    }

    override fun observables() {
        viewModel.listEvent.observe(this, Observer { events ->
            Log.d("List", events.toString())
            adapter.loadEvents(events)
        })
    }

    override fun setupUI() {
        adapter = EventsListAdapter(this)
        manager = LinearLayoutManager(this)
        list_events.layoutManager = manager
        list_events.adapter = adapter
    }
}