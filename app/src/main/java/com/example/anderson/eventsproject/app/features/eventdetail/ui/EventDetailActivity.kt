package com.example.anderson.eventsproject.app.features.eventdetail.ui

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.anderson.eventsproject.MyApplication
import com.example.anderson.eventsproject.R
import com.example.anderson.eventsproject.app.features.BaseActivity
import com.example.anderson.eventsproject.app.features.eventdetail.viewmodel.EventDetailViewModel
import com.example.anderson.eventsproject.app.features.events.ui.EventsActivity.Companion.ID_EVENT_INTENT
import com.example.anderson.eventsproject.app.util.FormatDate
import com.example.anderson.eventsproject.app.util.SharedUtil
import com.example.anderson.eventsproject.domain.model.CheckIn
import com.example.anderson.eventsproject.domain.model.Event
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.dialog_checkin.*
import javax.inject.Inject


class EventDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: EventDetailViewModel

    //Animations

    private val rotateOpen: Animation  by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim) }
    private val rotateClose: Animation  by lazy { AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim) }
    private val fromBottom: Animation  by lazy { AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim) }
    private val toBottom: Animation  by lazy { AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim) }

    var idEvent: Int = 0
    lateinit var dialog: Dialog
    private var clicked = false
    lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        (applicationContext as MyApplication).appComponent.presentationSubComponent().create()
            .inject(this)

        initialize()
    }

    override fun initialize() {

        viewModel = ViewModelProvider(this, viewModelFactory).get(EventDetailViewModel::class.java)

        dialog = Dialog(this, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.setContentView(R.layout.dialog_checkin)

        setupUI()
        observables()

        getIdEventParameter()

    }

    override fun observables() {
      viewModel.eventDetail.observe(this, Observer { eventDetails->

          event = eventDetails
          detail_title.text = eventDetails.title
          detail_date_event.text = FormatDate.formatDate(eventDetails.date)
          detail_description_event.text = eventDetails.description
          detail_price_event.text = eventDetails.price.toString()

          loadImage(eventDetails.image)
      })

        viewModel.formCheckInValid.observe(this, Observer {valid->
            dialog.btn_effect_checkin.isEnabled = valid
        })

        viewModel.nameMessageError.observe(this, Observer {errorMesage->
            if (errorMesage != "") {
                dialog.checkin_name.error = errorMesage
            }
        })

        viewModel.emailMessageError.observe(this, Observer {errorMesage->
            if (errorMesage != "") {
                dialog.checkin_email.error = errorMesage
            }
        })

        viewModel.checkInSucess.observe(this, Observer {sucess->
            if (sucess) {
                dialog.dismiss()
                showSnackBarCheckInSucess()
                dialog.checkin_name.text?.clear()
                dialog.checkin_email.text?.clear()
            }
        })
    }

    private fun loadImage(urlImg: String) {
        Picasso
            .with(this)
            .load(urlImg)
            .into(detail_image)
    }

    override fun setupUI() {
        toolAppBar.setNavigationOnClickListener {
            finish()
        }

        btn_options.setOnClickListener {
            onOptionsButtonClicked()
        }
        btn_share.setOnClickListener {
            if (event != null) {
                SharedUtil.sharedContent(event, this)
            }
        }
        btn_checkin.setOnClickListener {
            showDialogCheckIn()
            onOptionsButtonClicked()
        }

        dialog.checkin_name.afterTextChanged {
            viewModel.nameData(it)
        }

        dialog.checkin_email.afterTextChanged {
            viewModel.emailData(it)
        }

        dialog.btn_effect_checkin.setOnClickListener {
           viewModel.checkIn(CheckIn(dialog.checkin_name.text.toString(),dialog.checkin_email.text.toString(),idEvent.toString() ))
        }

    }

    private fun onOptionsButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            btn_share.visibility = View.VISIBLE
            btn_checkin.visibility = View.VISIBLE
        }else{
            btn_share.visibility = View.INVISIBLE
            btn_checkin.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            btn_share.startAnimation(fromBottom)
            btn_checkin.startAnimation(fromBottom)
            btn_options.startAnimation(rotateOpen)
        } else {
            btn_share.startAnimation(toBottom)
            btn_checkin.startAnimation(toBottom)
            btn_options.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean) {
        if (!clicked) {
            btn_checkin.isClickable = true
            btn_share.isClickable = true
        } else {
            btn_checkin.isClickable = false
            btn_share.isClickable = false
        }
    }

    private fun getIdEventParameter() {
        idEvent = intent.getIntExtra(ID_EVENT_INTENT, 0)
        viewModel.getEventDetails(idEvent)
    }

    private fun showDialogCheckIn() {
        dialog.show()
    }

    private fun showSnackBarCheckInSucess() {
        val snackBar = Snackbar.make(root_layout, "", Snackbar.LENGTH_LONG)
        val custom = layoutInflater.inflate(R.layout.snackbar_checkin_sucess, null)
        custom.setPadding(0, 0, 0, 0)
        snackBar.view.setBackgroundColor(Color.TRANSPARENT)
        snackBar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
        val snackLayout = snackBar.view as (Snackbar.SnackbarLayout)
        snackLayout.setPadding(0, 0, 0, 0)
        val params = snackBar.view.layoutParams as (ViewGroup.MarginLayoutParams)
        params.setMargins(0, 0, 0, 60)
        params.height =110
        snackBar.view.layoutParams = params
        snackLayout.addView(custom, 0)
        snackBar.show()
    }

}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}