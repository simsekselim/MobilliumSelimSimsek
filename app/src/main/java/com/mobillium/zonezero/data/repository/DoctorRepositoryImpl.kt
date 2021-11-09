package com.mobillium.zonezero.data.repository

import com.mobillium.zonezero.data.source.LocalDataSource
import com.mobillium.zonezero.data.source.RemoteDataSource
import com.mobillium.zonezero.domain.model.Doctors
import com.mobillium.zonezero.domain.model.base.DataHolder
import com.mobillium.zonezero.domain.repository.DoctorsRepository
import io.reactivex.Single
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : DoctorsRepository {

    override fun fetchDoctorList(): Single<DataHolder<Doctors>> {
        return remoteDataSource.getDoctors().onErrorReturn { DataHolder.Fail(Error(it.message)) }
    }

}