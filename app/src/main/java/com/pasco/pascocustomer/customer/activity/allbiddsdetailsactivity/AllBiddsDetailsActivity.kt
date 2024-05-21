package com.pasco.pascocustomer.customer.activity.allbiddsdetailsactivity

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.johncodeos.customprogressdialogexample.CustomProgressDialog
import com.pasco.pascocustomer.R
import com.pasco.pascocustomer.customer.activity.allbiddsdetailsactivity.adapter.AllBiddsDetailsAdapter
import com.pasco.pascocustomer.customer.activity.allbiddsdetailsactivity.model.AllBiddsDetailResponse
import com.pasco.pascocustomer.customer.activity.allbiddsdetailsactivity.model.BiddsDtailsModelView
import com.pasco.pascocustomer.databinding.ActivityAllBiddsDetailsBinding
import com.pasco.pascocustomer.utils.ErrorUtil
import dagger.hilt.android.AndroidEntryPoint
import java.lang.String.format
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class AllBiddsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllBiddsDetailsBinding
    private val progressDialog by lazy { CustomProgressDialog(this@AllBiddsDetailsActivity) }
    private var biddsDetailsList: List<AllBiddsDetailResponse.Datum> = ArrayList()
    private val detailsModel: BiddsDtailsModelView by viewModels()
    private var userName = ""
    private var orderId = ""
    private var dateTime = ""
    private var pickupLocation = ""
    private var dropLocation = ""
    private var distance = ""
    private var id = ""
    private var totalPrice = ""
    private var biddsDetailsAdapter: AllBiddsDetailsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllBiddsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userName = intent.getStringExtra("userName").toString()
        orderId = intent.getStringExtra("orderId").toString()
        dateTime = intent.getStringExtra("dateTime").toString()
        pickupLocation = intent.getStringExtra("pickupLocation").toString()
        dropLocation = intent.getStringExtra("dropLocation").toString()
        distance = intent.getStringExtra("distance").toString()
        id = intent.getStringExtra("id").toString()
        totalPrice = intent.getStringExtra("totalPrice").toString()

        binding.userName.text = userName
        binding.oderIdTxt.text = truncateBookingNumber(orderId)
        binding.oderIdTxt.setOnClickListener {
            showFullAddressDialog(orderId)
        }


        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.US)

        try {
            val parsedDate = inputDateFormat.parse(dateTime)
            outputDateFormat.timeZone = TimeZone.getDefault() // Set to local time zone
            val formattedDateTime = outputDateFormat.format(parsedDate)

            binding.dateTime.text = formattedDateTime
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        binding.pickUpLocation.text = pickupLocation
        binding.dropLocation.text = dropLocation

        val distanceValue = distance.toDoubleOrNull() ?: 0.0 // Convert distance to Double
        val formattedDistance = String.format("%.2f", distanceValue)
       binding.distanceTxt.text = "$formattedDistance Km"

        binding.totalPriceTxt.text ="$ $totalPrice"

        binding.backBtn.setOnClickListener { finish() }

        getBiddsDetailsList()
        biddsDetailsObserver()

    }


    private fun getBiddsDetailsList() {
        detailsModel.getBidds(id, this, progressDialog)
    }

    @SuppressLint("SetTextI18n")
    private fun biddsDetailsObserver() {
        detailsModel.progressIndicator.observe(this@AllBiddsDetailsActivity) {
        }
        detailsModel.mRejectResponse.observe(this@AllBiddsDetailsActivity) {
            val message = it.peekContent().msg
            val success = it.peekContent().status


            if (success == "True") {
                biddsDetailsList = it.peekContent().data!!
                val guestSize = biddsDetailsList.size
                Log.e("eventId", "onCreateSize: " + biddsDetailsList.size)
                //binding.joinDate.text=joinDate.toString()
                binding.detailsRecycler.isVerticalScrollBarEnabled = true
                binding.detailsRecycler.isVerticalFadingEdgeEnabled = true
                binding.detailsRecycler.layoutManager =
                    LinearLayoutManager(
                        this@AllBiddsDetailsActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                biddsDetailsAdapter =
                    AllBiddsDetailsAdapter(this@AllBiddsDetailsActivity, biddsDetailsList)
                binding.detailsRecycler.adapter = biddsDetailsAdapter


            }

        }
        detailsModel.errorResponse.observe(this@AllBiddsDetailsActivity) {
            ErrorUtil.handlerGeneralError(this@AllBiddsDetailsActivity, it)
            //errorDialogs()
        }
    }

    private fun truncateBookingNumber(bookingNumber: String, maxLength: Int = 8): String {
        return if (bookingNumber.length > maxLength) {
            "${bookingNumber.substring(0, maxLength)}..."
        } else {
            bookingNumber
        }
    }

    private fun showFullAddressDialog(fullBookingNumber: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Order ID")
        alertDialogBuilder.setMessage(fullBookingNumber)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}