package com.example.anderson.eventsproject.app.features.events.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anderson.eventsproject.MyApplication
import com.example.anderson.eventsproject.R
import com.example.anderson.eventsproject.app.features.BaseActivity
import com.example.anderson.eventsproject.app.features.eventdetail.ui.EventDetailActivity
import com.example.anderson.eventsproject.app.features.events.viewmodel.EventsViewModel
import com.example.anderson.eventsproject.domain.model.Event
import kotlinx.android.synthetic.main.activity_events.*
import javax.inject.Inject

class EventsActivity : BaseActivity(), Router {

    private lateinit var viewModel: EventsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var manager: LinearLayoutManager
    private lateinit var adapter: EventsListAdapter

    companion object{
        const val ID_EVENT_INTENT = "idEvent"
    }

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

        viewModel.loading.observe(this, Observer {
            if(it){
                loading_event.visibility = View.VISIBLE
            }else {
                loading_event.visibility = View.GONE
            }
        })

        viewModel.isHasInternet.observe(this, Observer {
            if (!it) {
                Toast.makeText(this, "Sem conexão!!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun setupUI() {
        adapter = EventsListAdapter(this,this)
        manager = LinearLayoutManager(this)
        list_events.layoutManager = manager
        list_events.adapter = adapter
    }

    override fun toGoDetailScreen(event: Event) {
        event.let {
            val intent = Intent(this, EventDetailActivity::class.java)
            intent.putExtra(ID_EVENT_INTENT,event.id);
            startActivity(intent)
        }
    }
}