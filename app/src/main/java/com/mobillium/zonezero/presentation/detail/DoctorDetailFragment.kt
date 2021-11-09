package com.mobillium.zonezero.presentation.detail

import android.os.Bundle
import com.mobillium.zonezero.R
import com.mobillium.zonezero.databinding.FragmentDoctorDetailBinding
import com.mobillium.zonezero.domain.model.Doctor
import com.mobillium.zonezero.domain.model.UserStatus
import com.mobillium.zonezero.presentation.Const.SELECTED_DOCTOR
import com.mobillium.zonezero.presentation.activity.MainActivity
import com.mobillium.zonezero.presentation.base.BaseFragment
import com.mobillium.zonezero.presentation.meeting.MeetingFragment
import com.mobillium.zonezero.presentation.payment.PaymentFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DoctorDetailFragment : BaseFragment<DoctorDetailViewModel, FragmentDoctorDetailBinding>(
    R.layout.fragment_doctor_detail,
    DoctorDetailViewModel::class.java
) {

    override fun initView() {
        binding.rlRouting.setOnClickListener {
            viewModel.isPremium.value?.let {
                val routingFragment = if (it)
                    MeetingFragment.newInstance()
                else
                    PaymentFragment.newInstance()

                MainActivity.addFragment(routingFragment, parentFragmentManager)
            }
        }
    }

    override fun observeLiveData() {}

    override fun getSafeArgs() {
        arguments?.getSerializable(SELECTED_DOCTOR)?.let {
            viewModel.selectedDoctor.value = it as Doctor
            viewModel.isPremium.value = it.user_status == UserStatus.PREMIUM.type
        }
    }

    companion object {
        fun newInstance(selectedDoctor: Doctor): DoctorDetailFragment =
            DoctorDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SELECTED_DOCTOR, selectedDoctor)
                }
            }
    }
}