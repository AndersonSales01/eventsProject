package com.example.anderson.eventsproject.presentation.features.events.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.anderson.eventsproject.presentation.util.FormatDate
import com.example.anderson.eventsproject.domain.model.Event
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_events.view.*


class EventViewHolder(private val context: Context, private val router: Router, itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun bindView(event: Event) {
        itemView.title_event.text = event.title
        itemView.price_event.text = event.price.toString()
        itemView.date_event.text = FormatDate.formatDate(event.date)
        loadImage(event.image)

        itemView.setOnClickListener {
            router.toGoDetailScreen(event)
        }
    }

    private fun loadImage(urlImg: String) {
        Picasso
            .with(context)
            .load(urlImg)
            .into(itemView.image_event)
    }
}