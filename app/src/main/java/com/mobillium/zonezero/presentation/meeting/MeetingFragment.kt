package com.mobillium.zonezero.presentation.meeting

import com.mobillium.zonezero.R
import com.mobillium.zonezero.databinding.FragmentMeetingBinding
import com.mobillium.zonezero.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeetingFragment : BaseFragment<MeetingViewModel, FragmentMeetingBinding>(
    R.layout.fragment_meeting,
    MeetingViewModel::class.java
) {
    override fun initView() {}
    override fun observeLiveData() {}
    override fun getSafeArgs() {}

    companion object {
        fun newInstance(): MeetingFragment = MeetingFragment()
    }
}