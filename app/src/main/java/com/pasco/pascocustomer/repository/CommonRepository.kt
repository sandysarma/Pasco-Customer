package com.pasco.pascocustomer.repository

import VehicleTypeResponse
import com.pasco.pascocustomer.application.PascoApp
import com.pasco.pascocustomer.commonpage.login.loginmodel.LoginBody
import com.pasco.pascocustomer.commonpage.login.loginmodel.LoginResponse
import com.pasco.pascocustomer.commonpage.login.loginotpcheck.OtpCheckResponse
import com.pasco.pascocustomer.commonpage.login.signup.clientmodel.ClientSignUpResponse
import com.pasco.pascocustomer.commonpage.login.signup.clientmodel.ClientSignupBody
import com.pasco.pascocustomer.customer.activity.allbiddsdetailsactivity.model.AllBiddsDetailResponse
import com.pasco.pascocustomer.customer.activity.hometabactivity.cargoavailable.CargoAvailableBody
import com.pasco.pascocustomer.customer.activity.hometabactivity.cargoavailable.CargoAvailableResponse
import com.pasco.pascocustomer.customer.activity.hometabactivity.checkservicemodel.CheckChargesBody
import com.pasco.pascocustomer.customer.activity.hometabactivity.checkservicemodel.CheckChargesResponse
import com.pasco.pascocustomer.customer.activity.hometabactivity.modelview.BookingOrderBody
import com.pasco.pascocustomer.customer.activity.hometabactivity.modelview.BookingRideResponse
import com.pasco.pascocustomer.customer.activity.notificaion.delete.DeleteNotificationResponse
import com.pasco.pascocustomer.customer.activity.notificaion.modelview.NotificationResponse
import com.pasco.pascocustomer.services.ApiServices
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
import javax.inject.Inject

class CommonRepository @Inject constructor(private val apiService: ApiServices) {
    fun getClientSign(courseBody: ClientSignupBody): Observable<ClientSignUpResponse> {
        return apiService.userRegister(courseBody)
    }

    fun getOtpCheck(courseBody: ClientSignupBody): Observable<OtpCheckResponse> {
        return apiService.otpCheck(courseBody)
    }

    fun getLogin(courseBody: LoginBody): Observable<LoginResponse> {
        return apiService.userLogin(courseBody)
    }

    fun getServicesRepo(): Observable<ServicesResponse> {
        return apiService.getServicesList()
    }

    fun getVehicleType(shipment_type: String): Observable<VehicleTypeResponse> {
        return apiService.getCargoList(PascoApp.encryptedPrefs.bearerToken, shipment_type)
    }

    fun getApprovalReqRepo(
        cargo: RequestBody,
        vehiclenumber: RequestBody,
        identify_document: MultipartBody.Part,
        identify_document1: MultipartBody.Part,
        identify_document2: MultipartBody.Part

    ): Observable<ApprovalRequestResponse> {
        return apiService.getApprovalRequest(
            PascoApp.encryptedPrefs.bearerToken,
            cargo, vehiclenumber, identify_document, identify_document1, identify_document2
        )
    }


    fun bookingOrder(courseBody: BookingOrderBody): Observable<BookingRideResponse> {
        return apiService.bookRideServices(PascoApp.encryptedPrefs.bearerToken, courseBody)
    }

    fun checkCharge(courseBody: CheckChargesBody): Observable<CheckChargesResponse> {
        return apiService.getCharges(PascoApp.encryptedPrefs.bearerToken, courseBody)
    }

    fun cargoAvailable(courseBody: CargoAvailableBody): Observable<CargoAvailableResponse> {
        return apiService.cargoAvailable(PascoApp.encryptedPrefs.bearerToken, courseBody)
    }

    fun logOut(courseBody: LogoutBody): Observable<LogoutResponse> {
        return apiService.logOut(PascoApp.encryptedPrefs.bearerToken, courseBody)
    }

    fun getOrder(): Observable<OrderResponse> {
        return apiService.getOrder(PascoApp.encryptedPrefs.bearerToken)
    }

    fun getAllBidds(): Observable<OrderResponse> {
        return apiService.getAllBids(PascoApp.encryptedPrefs.bearerToken)
    }

    fun getAllBiddsDetails(Id: String): Observable<AllBiddsDetailResponse> {
        return apiService.getBiddsDetails(PascoApp.encryptedPrefs.bearerToken, Id)
    }

    fun getAcceptedBids(): Observable<AllBiddsDetailResponse> {
        return apiService.acceptedBids(PascoApp.encryptedPrefs.bearerToken)
    }



    fun updateProfile(
        full_name: RequestBody,
        email: RequestBody,
        current_city: RequestBody,
        image: MultipartBody.Part
    )
            : Observable<UpdateProfileResponse> {
        return apiService.updateProfile(
            PascoApp.encryptedPrefs.bearerToken,
            full_name,
            email,
            current_city,
            image
        )
    }

    fun getUserNotification(): Observable<NotificationResponse> {
        return apiService.getNotification(PascoApp.encryptedPrefs.bearerToken)
    }

    fun getDeleteNotification(Id: String): Observable<DeleteNotificationResponse> {
        return apiService.deleteNotifications(PascoApp.encryptedPrefs.bearerToken, Id)
    }

    fun getCountNotifications(): Observable<NotificationCountResponse> {
        return apiService.getCountNotification(
            PascoApp.encryptedPrefs.bearerToken
        )
    }

    fun getProfile(): Observable<GetProfileResponse> {
        return apiService.getUserProfile(PascoApp.encryptedPrefs.bearerToken)
    }
}
