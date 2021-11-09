package com.mobillium.zonezero.presentation.detail


import androidx.lifecycle.MutableLiveData
import com.mobillium.zonezero.domain.model.Doctor
import com.mobillium.zonezero.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorDetailViewModel@Inject constructor(): BaseViewModel() {
    var selectedDoctor: MutableLiveData<Doctor> = MutableLiveData()
    var isPremium: MutableLiveData<Boolean> = MutableLiveData(false)
}