package com.example.anderson.eventsproject.app.features.events.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anderson.eventsproject.R
import com.example.anderson.eventsproject.domain.model.Event

class EventsListAdapter(private val context: Context,private val router: Router) : RecyclerView.Adapter<EventViewHolder>() {

    private var listEvents: List<Event> = mutableListOf()

    fun loadEvents(list: List<Event>) {
        this.listEvents = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_events, parent, false)

        return EventViewHolder(context,router,view)
    }

    override fun getItemCount(): Int {
        return listEvents.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = listEvents[position]
        holder.bindView(event)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}