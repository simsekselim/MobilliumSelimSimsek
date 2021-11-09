package com.mobillium.zonezero.domain.usecase

import com.mobillium.zonezero.domain.model.Doctors
import com.mobillium.zonezero.domain.model.base.DataHolder
import com.mobillium.zonezero.domain.repository.DoctorsRepository
import com.mobillium.zonezero.domain.usecase.base.BaseUseCase
import io.reactivex.Single
import javax.inject.Inject


class DoctorsListUseCase @Inject constructor(
    private val repository: DoctorsRepository
) : BaseUseCase.RequestInteractor<Any, Doctors>() {

    override fun executeAsync(params: Any): Single<DataHolder<Doctors>> {
        return repository.fetchDoctorList()
    }

}