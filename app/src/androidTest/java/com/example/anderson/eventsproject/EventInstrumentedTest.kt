package com.example.anderson.eventsproject

import android.service.autofill.Validators.not
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.anderson.eventsproject.app.features.events.ui.EventViewHolder
import com.example.anderson.eventsproject.app.features.events.ui.EventsActivity
import com.example.anderson.eventsproject.app.features.events.ui.EventsListAdapter
import org.hamcrest.Matchers.isEmptyString

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.regex.Pattern.matches

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class EventInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(EventsActivity::class.java)

    @Test
    fun eventDetailInstrumentTest() {

        Thread.sleep(3000)

        Espresso.onView(withId(R.id.list_events))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.list_events)).perform(
            RecyclerViewActions.actionOnItemAtPosition<EventViewHolder>(0, ViewActions.click()))

       // Espresso.onView(withId(R.id.detail_title)).check(ViewAssertions.matches(withText(isEmptyString())))
        //Espresso.onView(withId(R.id.detail_title)).check(ViewAssertions.matches(not(withText(""))));
        Thread.sleep(3000)

    }


    @Test
    fun checkInEventInstrumentTestSucess() {

        Thread.sleep(3000)

        Espresso.onView(withId(R.id.list_events))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.list_events)).perform(
            RecyclerViewActions.actionOnItemAtPosition<EventViewHolder>(3, ViewActions.click()))

        Espresso.onView(withId(R.id.btn_options)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.btn_checkin)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.layout_checkin_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.checkin_name))
            .perform(ViewActions.typeText("Anderson"))

        Espresso.onView(withId(R.id.checkin_email))
            .perform(ViewActions.typeText("Anderson@hotmail.com"))

        // closed Key board
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

         Espresso.onView(withId(R.id.btn_effect_checkin)).perform(ViewActions.click())

        Thread.sleep(3000)

        Espresso.onView(withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(3000)


    }
}