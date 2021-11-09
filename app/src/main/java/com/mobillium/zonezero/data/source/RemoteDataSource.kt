package com.mobillium.zonezero.data.source

import com.mobillium.zonezero.data.api.ApiService
import com.mobillium.zonezero.domain.model.Doctors
import com.mobillium.zonezero.domain.model.base.DataHolder
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getDoctors(
    ): Single<DataHolder<Doctors>> {
        return apiService.fetchDoctors(
        ).map { response ->
            return@map DataHolder.Success(response)
        }
    }
}