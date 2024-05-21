package com.pasco.pascocustomer.services

import VehicleTypeResponse
import com.example.transportapp.DriverApp.MarkDuty.MarkDutyResponse
import com.pasco.pascocustome.Driver.Customer.Fragment.CustomerWallet.AddAmountResponse
import com.pasco.pascocustome.Driver.Customer.Fragment.CustomerWallet.GetAmountResponse
import com.pasco.pascocustomer.Driver.AcceptRideDetails.ViewModel.AcceptRideResponse
import com.pasco.pascocustomer.Driver.AcceptRideDetails.ViewModel.AddBiddingBody
import com.pasco.pascocustomer.Driver.AcceptRideDetails.ViewModel.AddBidingResponse
import com.pasco.pascocustomer.Driver.ApprovalStatus.ViewModel.ApprovalStatusResponse
import com.pasco.pascocustomer.Driver.CouponDetails.CouponViewModel.CouponResponse
import com.pasco.pascocustomer.Driver.CouponDetails.CouponViewModel.CouponUsedResponse
import com.pasco.pascocustomer.Driver.EmergencyResponse.ViewModel.EmergencyCResponse
import com.pasco.pascocustomer.Driver.Fragment.DriverAllBiddsDetail.ViewModel.GetDriverBidDetailsDataResponse
import com.pasco.pascocustomer.Driver.Fragment.DriverOrders.ViewModel.DAllOrderResponse
import com.pasco.pascocustomer.Driver.Fragment.HomeFrag.ViewModel.ShowBookingReqResponse
import com.pasco.pascocustomer.Driver.NotesRemainders.ViewModel.NotesRResponse
import com.pasco.pascocustomer.Driver.StartRiding.ViewModel.GetRouteUpdateResponse
import com.pasco.pascocustomer.Driver.StartRiding.ViewModel.StartTripResponse
import com.pasco.pascocustomer.Driver.StartRiding.ViewModel.UpDriverStatusResponse
import com.pasco.pascocustomer.Driver.UpdateLocation.UpdateLocationResponse
import com.pasco.pascocustomer.Driver.UpdateLocation.UpdationLocationBody
import com.pasco.pascocustomer.Profile.PutViewModel.ProfileResponse
import com.pasco.pascocustomer.application.PascoApp
import com.pasco.pascocustomer.commonpage.login.loginmodel.LoginBody
import com.pasco.pascocustomer.commonpage.login.loginmodel.LoginResponse
import com.pasco.pascocustomer.commonpage.login.loginotpcheck.OtpCheckResponse
import com.pasco.pascocustomer.commonpage.login.signup.clientmodel.ClientSignUpResponse
import com.pasco.pascocustomer.commonpage.login.signup.clientmodel.ClientSignupBody
import com.pasco.pascocustomer.commonpage.login.signup.model.DriverBody
import com.pasco.pascocustomer.commonpage.login.signup.model.DriverResponse
import com.pasco.pascocustomer.customer.activity.allbiddsdetailsactivity.model.AllBiddsDetailResponse
import com.pasco.pascocustomer.customer.activity.hometabactivity.cargoavailable.CargoAvailableBody
import com.pasco.pascocustomer.customer.activity.hometabactivity.cargoavailable.CargoAvailableResponse
import com.pasco.pascocustomer.customer.activity.hometabactivity.checkservicemodel.CheckChargesBody
import com.pasco.pascocustomer.customer.activity.hometabactivity.checkservicemodel.CheckChargesResponse
import com.pasco.pascocustomer.customer.activity.hometabactivity.modelview.BookingOrderBody
import com.pasco.pascocustomer.customer.activity.hometabactivity.modelview.BookingRideResponse
import com.pasco.pascocustomer.customer.activity.notificaion.delete.DeleteNotificationResponse
import com.pasco.pascocustomer.customer.activity.notificaion.modelview.NotificationResponse
import com.pasco.pascocustomer.userFragment.logoutmodel.LogoutBody
import com.pasco.pascocustomer.userFragment.logoutmodel.LogoutResponse
import com.pasco.pascocustomer.userFragment.order.odermodel.OrderResponse
import com.pasco.pascocustomer.userFragment.profile.updatemodel.UpdateProfileResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import com.pasco.pascocustomer.activity.Driver.AddVehicle.ApprovalRequest.ApprovalRequestResponse
import com.pasco.pascocustomer.customer.activity.notificaion.notificationcount.NotificationCountResponse
import com.pasco.pascocustomer.customer.activity.vehicledetailactivity.adddetailsmodel.ServicesResponse
import com.pasco.pascocustomer.userFragment.profile.modelview.GetProfileResponse
import retrofit2.http.*


interface ApiServices {
    @Headers("Accept:application/json")
    @POST("user-registration/")
    fun driverRegister(
        @Body body: DriverBody
    ): Observable<DriverResponse>

    @Headers("Accept:application/json")
    @POST("client-registration/")
    fun userRegister(
        @Body body: ClientSignupBody
    ): Observable<ClientSignUpResponse>

    @Headers("Accept:application/json")
    @POST("check-login/")
    fun otpCheck(
        @Body body: ClientSignupBody
    ): Observable<OtpCheckResponse>

    @Headers("Accept:application/json")
    @POST("user-login/")
    fun userLogin(
        @Body body: LoginBody
    ): Observable<LoginResponse>
    //services List
    @GET("service-lists/")
    fun getServicesList(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
    ): Observable<ServicesResponse>


    //vehicle Type
    @FormUrlEncoded
    @POST("cargo-lists/")
    fun getCargoList(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Field("shipment_type") shipment_type: String
    ): Observable<VehicleTypeResponse>

    //approval request
    @Multipart
    @Headers("Accept: application/json")
    @POST("approvalrequest/")
    fun getApprovalRequest(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Part("cargo") cargo: RequestBody,
        @Part("vehiclenumber") vehiclenumber: RequestBody,
        @Part attachment: MultipartBody.Part,
        @Part attachment1: MultipartBody.Part,
        @Part attachment2: MultipartBody.Part
    ): Observable<ApprovalRequestResponse>

    @Headers("Accept:application/json")
    @POST("ride-booking/")
    fun bookRideServices(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Body body: BookingOrderBody
    ): Observable<BookingRideResponse>


    @Headers("Accept:application/json")
    @POST("checkcharges/")
    fun getCharges(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Body body: CheckChargesBody
    ): Observable<CheckChargesResponse>

    @Headers("Accept:application/json")
    @POST("checkavailability/")
    fun cargoAvailable(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Body body: CargoAvailableBody
    ): Observable<CargoAvailableResponse>

    @Headers("Accept:application/json")
    @POST("user-logout/")
    fun logOut(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Body body: LogoutBody
    ): Observable<LogoutResponse>

    @GET("request-send/")
    fun getOrder(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken
    ): Observable<OrderResponse>

    @GET("afterbidlist/")
    fun getAllBids(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken
    ): Observable<OrderResponse>

    @GET("bookingbiddetail/{id}")
    fun getBiddsDetails(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Path("id") Id: String
    ): Observable<AllBiddsDetailResponse>

    @GET("acceptedbooking/")
    fun acceptedBids(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken
    ): Observable<AllBiddsDetailResponse>

    @GET("user-update-profile/")
    fun getProfileDetails(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken
    ): Observable<GetProfileResponse>

    @Multipart
    @Headers("Accept:application/json")
    @PUT("user-update-profile/")
    fun updateProfile(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Part("full_name") full_name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("current_city") current_city: RequestBody,
        @Part image: MultipartBody.Part,
        ): Observable<UpdateProfileResponse>

    @GET("ShowNotification/")
    fun getNotification(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken
    ): Observable<NotificationResponse>

    @GET("NotificationDelete/{user_id}")
    fun deleteNotifications(
        @Path("user_id") user_id: String,
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
    ): Observable<DeleteNotificationResponse>




    //approval status
    @GET("approvalstatus/")
    fun getApprovedData(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken
    ): Observable<ApprovalStatusResponse>

    //duty On
    @PUT("marked-duty-status/")
    fun putMarkDuty(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken
    ): Observable<MarkDutyResponse>

    //showBooking request data
    @GET("showbookingriderequests/")
    fun getBookingReq(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
    ): Observable<ShowBookingReqResponse>

    //get update BID details
    @GET("updatebookingbid/{id}/")
    fun getBidDetails(
        @Path("id") id: String,
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken
    ): Observable<AcceptRideResponse>

    //get update and Add BID data
    @PUT("updatebookingbid/{id}/")
    fun addBidDetails(
        @Path("id") id: String,
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Body addBidDetails: AddBiddingBody
    ): Observable<AddBidingResponse>

    //get all orders
    @GET("bookingdriverbiddetail/")
    fun getAllOrderDriver(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
    ): Observable<DAllOrderResponse>

    //all bids details
    @GET("bookingdriverdata/{bookingId}/")
    fun bookingDriverData(
        @Path("bookingId") bookingId: String,
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
    ):Observable<GetDriverBidDetailsDataResponse>


    //driver status list
    @GET("driver_status_list/")
    fun getDriverStatus(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
    ): Observable<GetRouteUpdateResponse>
    //update driver status
    @FormUrlEncoded
    @POST("update-driver-status/{id}/")
    fun getUpdateDriverStatus(
        @Path("id") id: String,
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Field("driver_status") driver_status: String
    ): Observable<UpDriverStatusResponse>

    //start trip driver
    @FormUrlEncoded
    @POST("start_trip/{id}/")
    fun startTrip(
        @Path("id") id: String,
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Field("driver_status") driver_status: String
    ): Observable<StartTripResponse>

    //get profile
    @GET("user-update-profile/")
    fun getUserProfile(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
    ): Observable<GetProfileResponse>

    //put profile
    @Multipart
    @PUT("user-update-profile/")
    fun putProfile(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Part("full_name") full_name: RequestBody,
        @Part("email") email: RequestBody,
        @Part Attachment: MultipartBody.Part
    ): Observable<ProfileResponse>

    @FormUrlEncoded
    @POST("user-logout/")
    fun getUserLogout(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Field("refresh") refresh: String
    ): Observable<LogoutResponse>

    //CouponList
    @GET("coupon-detail/")
    fun getCouponList(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
    ): Observable<CouponResponse>

    //coupon used Api
    @FormUrlEncoded
    @POST("add-couponused/")
    fun getCouponUsed(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Field("coupon_used") coupon_used: String
    ): Observable<CouponUsedResponse>

    //EmergencyList
    @GET("show-emergencydetail/")
    fun getEmergencyList(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
    ): Observable<EmergencyCResponse>



    @GET("UnreadNotification/")
    fun getCountNotification(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
    ): Observable<NotificationCountResponse>

    //addNotesReminder
    @FormUrlEncoded
    @POST("add-reminder/")
    fun addNotesReminder(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("reminderdate") reminderdate: String,
    ): Observable<NotesRResponse>

    //update driver location
    @Headers("Accept: application/json")
    @POST("location-update/")
    fun updateGeolocation(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Body body: UpdationLocationBody,
    ): Observable<UpdateLocationResponse>

    //user Api

    @FormUrlEncoded
    @POST("client-registration/")
    fun getClientReg(
        @Field("phone_number") phone_number: String,
        @Field("user_type") user_type: String,
        @Field("phone_verify") phone_verify: String
    ): Observable<ClientSignUpResponse>

    @FormUrlEncoded
    @POST("checkcharges/")
    fun getCharges(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Field("cargo") cargo: String,
        @Field("pickup_location") pickup_location: String,
        @Field("drop_location") drop_location: String,
        @Field("pickup_latitude") pickup_latitude: String,
        @Field("pickup_longitude") pickup_longitude: String,
        @Field("drop_latitude") drop_latitude: String,
        @Field("drop_longitude") drop_longitude: String
    ): Observable<CheckChargesResponse>


    @FormUrlEncoded
    @POST("ride-booking/")
    fun bookRideServices(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Field("cargo") cargo: String,
        @Field("cargo_quantity") cargo_quantity: String,
        @Field("pickup_location") pickup_location: String,
        @Field("drop_location") drop_location: String,
        @Field("pickup_city") pickup_city: String,
        @Field("drop_city") drop_city: String,
        @Field("pickup_latitude") pickup_latitude: String,
        @Field("pickup_longitude") pickup_longitude: String,
        @Field("drop_latitude") drop_latitude: String,
        @Field("drop_longitude") drop_longitude: String,
        @Field("pickup_datetime") pickup_datetime: String,
    ): Observable<BookingRideResponse>



    //add wallet Money
    @FormUrlEncoded
    @POST("addUserWallet/")
    fun addUserWallet(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken,
        @Field("amount") amount: String
    ):Observable<AddAmountResponse>


    //get User wallet balance
    @GET("addUserWallet/")
    fun getUserWallet(
        @Header("Authorization") token: String = PascoApp.encryptedPrefs.bearerToken
    ):Observable<GetAmountResponse>



}
