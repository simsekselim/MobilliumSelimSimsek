package com.mobillium.zonezero.domain.repository

import com.mobillium.zonezero.domain.model.Doctors
import com.mobillium.zonezero.domain.model.base.DataHolder
import io.reactivex.Single

interface DoctorsRepository {
    fun fetchDoctorList(): Single<DataHolder<Doctors>>
}