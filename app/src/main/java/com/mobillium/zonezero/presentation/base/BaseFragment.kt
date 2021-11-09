package com.mobillium.zonezero.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.mobillium.zonezero.BR
import androidx.lifecycle.ViewModelProvider
import com.mobillium.zonezero.presentation.activity.MainActivity

abstract class BaseFragment<TViewModel: BaseViewModel, TBinding: ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    private val viewModelType: Class<TViewModel>)
    : Fragment() {

    protected lateinit var viewModel: TViewModel
    protected lateinit var binding: TBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(viewModelType)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getSafeArgs()
        observeLiveData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initView()
    }

    private fun initBinding(){
        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = this
    }

    fun showLoading() = activity?.let { if(it is MainActivity) it.showLoading() }

    fun hideLoading() = activity?.let { if(it is MainActivity)  it.hideLoading() }

    abstract fun initView()

    abstract fun getSafeArgs()

    abstract fun observeLiveData()
}