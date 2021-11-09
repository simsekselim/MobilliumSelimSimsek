package com.mobillium.zonezero.presentation.payment

import com.mobillium.zonezero.R
import com.mobillium.zonezero.databinding.FragmentPaymentBinding
import com.mobillium.zonezero.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : BaseFragment<PaymentViewModel, FragmentPaymentBinding>(
    R.layout.fragment_payment,
    PaymentViewModel::class.java
) {
    override fun initView() {}
    override fun observeLiveData() {}
    override fun getSafeArgs() {}

    companion object {
        fun newInstance(): PaymentFragment = PaymentFragment()
    }
}