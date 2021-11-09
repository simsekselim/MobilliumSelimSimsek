package com.mobillium.zonezero.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mobillium.zonezero.domain.model.Doctor
import com.mobillium.zonezero.domain.model.Doctors
import com.mobillium.zonezero.domain.model.Gender
import com.mobillium.zonezero.domain.model.base.DataHolder
import com.mobillium.zonezero.domain.usecase.DoctorsListUseCase
import com.mobillium.zonezero.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorListViewModel @Inject constructor(
    private val listUseCase: DoctorsListUseCase
) : BaseViewModel() {

    var isCheckedWoman: MutableLiveData<Boolean> = MutableLiveData(false)
    var isCheckedMan: MutableLiveData<Boolean> = MutableLiveData(false)
    var selectedGender: MutableLiveData<Gender> = MutableLiveData(Gender.DEFAULT)

    var filterText: MutableLiveData<String> = MutableLiveData()
    var doctorList: MutableLiveData<List<Doctor>> = MutableLiveData()
    var isUserIncluded: MutableLiveData<Boolean> = MutableLiveData(true)

    fun genderSelected(gender: Gender, selected: Boolean) {
        when (gender) {
            Gender.MALE -> {
                if (selected) {
                    isCheckedMan.value = true
                    isCheckedWoman.value = false
                    selectedGender.value = Gender.MALE
                } else if(selectedGender.value == gender) {
                    defaultGenderFilter()
                }
            }
            Gender.FEMALE -> {
                if (selected) {
                    isCheckedWoman.value = true
                    isCheckedMan.value = false
                    selectedGender.value = Gender.FEMALE
                } else if(selectedGender.value == gender) {
                    defaultGenderFilter()
                }
            }
        }
    }

    private fun defaultGenderFilter(){
        isCheckedWoman.value = false
        isCheckedMan.value = false
        selectedGender.value = Gender.DEFAULT
    }

    private val _doctorsLiveData = MediatorLiveData<DataHolder<Doctors>>()
    val doctorsLiveData: LiveData<DataHolder<Doctors>>
        get() = _doctorsLiveData

    fun getDoctors() {
        _doctorsLiveData.value = DataHolder.Loading
        listUseCase.fetch(compositeDisposable, Any(), this::getDoctorsResponse)
    }

    private fun getDoctorsResponse(response: DataHolder<Doctors>) {
        when (response) {
            is DataHolder.Success -> {
                _doctorsLiveData.value = DataHolder.Success(response.data)
            }
            is DataHolder.Fail -> {
                _doctorsLiveData.value = DataHolder.Fail(response.e)
            }
        }
    }

}