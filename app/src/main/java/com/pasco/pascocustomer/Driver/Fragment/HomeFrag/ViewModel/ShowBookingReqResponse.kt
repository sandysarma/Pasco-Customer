package com.pasco.pascocustomer.Driver.Fragment.HomeFrag.ViewModel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ShowBookingReqResponse:Serializable{
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("msg")
    @Expose
    var msg: String? = null

    @SerializedName("data")
    @Expose
    var data: List<ShowBookingReqData>? = null

    inner class ShowBookingReqData:Serializable{

        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("booking_number")
        @Expose
        var bookingNumber: String? = null

        @SerializedName("user")
        @Expose
        var user: String? = null

        @SerializedName("user_image")
        @Expose
        var userImage: String? = null

        @SerializedName("driver")
        @Expose
        var driver: String? = null

        @SerializedName("driver_image")
        @Expose
        var driverImage: String? = null

        @SerializedName("shipmentname")
        @Expose
        var shipmentname: String? = null

        @SerializedName("vehiclename")
        @Expose
        var vehiclename: String? = null

        @SerializedName("pickup_location")
        @Expose
        var pickupLocation: String? = null

        @SerializedName("pickup_latitude")
        @Expose
        var pickupLatitude: Float? = null

        @SerializedName("pickup_longitude")
        @Expose
        var pickupLongitude: Float? = null

        @SerializedName("drop_location")
        @Expose
        var dropLocation: String? = null

        @SerializedName("drop_latitude")
        @Expose
        var dropLatitude: Float? = null

        @SerializedName("drop_longitude")
        @Expose
        var dropLongitude: Float? = null

        @SerializedName("total_distance")
        @Expose
        var totalDistance: Float? = null

        @SerializedName("basicprice")
        @Expose
        var basicprice: Float? = null

        @SerializedName("commision_price")
        @Expose
        var commisionPrice: Float? = null

        @SerializedName("message")
        @Expose
        var message: Any? = null

        @SerializedName("payment_method")
        @Expose
        var paymentMethod: Any? = null

        @SerializedName("customer_status")
        @Expose
        var customerStatus: String? = null

        @SerializedName("payment_status")
        @Expose
        var paymentStatus: String? = null

        @SerializedName("pickup_datetime")
        @Expose
        var pickupDatetime: String? = null

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

    }
}