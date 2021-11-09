package com.mobillium.zonezero.data.api

import com.mobillium.zonezero.domain.model.Doctors
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService{

    @GET("doctors.json")
    fun fetchDoctors() : Single<Doctors?>

}