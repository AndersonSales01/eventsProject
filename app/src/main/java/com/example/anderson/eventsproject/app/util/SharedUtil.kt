package com.example.anderson.eventsproject.app.util

import android.content.Context
import android.content.Intent
import com.example.anderson.eventsproject.domain.model.Event

object SharedUtil {

    fun sharedContent(event: Event, context: Context) {
        val intent = Intent(Intent.ACTION_SEND)

        intent.type = "text/plain"

        intent.putExtra(Intent.EXTRA_SUBJECT, event.title)

        intent.putExtra(Intent.EXTRA_TEXT, event.description + " - " + event.image)

        context.startActivity(
            Intent.createChooser(
                intent,
               "text"
            )
        )
    }
}