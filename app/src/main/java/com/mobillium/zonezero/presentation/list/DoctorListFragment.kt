package com.mobillium.zonezero.presentation.list

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.mobillium.zonezero.R
import com.mobillium.zonezero.common.extensions.addMarginDivider
import com.mobillium.zonezero.common.extensions.gone
import com.mobillium.zonezero.common.extensions.visible
import com.mobillium.zonezero.databinding.FragmentDoctorListBinding
import com.mobillium.zonezero.domain.model.Doctor
import com.mobillium.zonezero.domain.model.Gender
import com.mobillium.zonezero.domain.model.base.DataHolder
import com.mobillium.zonezero.presentation.DoctorsAdapter
import com.mobillium.zonezero.presentation.activity.MainActivity
import com.mobillium.zonezero.presentation.base.BaseFragment
import com.mobillium.zonezero.presentation.detail.DoctorDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class DoctorListFragment : BaseFragment<DoctorListViewModel, FragmentDoctorListBinding>(
    R.layout.fragment_doctor_list,
    DoctorListViewModel::class.java
) {

    lateinit var doctorListAdapter: DoctorsAdapter
    private var searchItemTextListener: BehaviorSubject<String>? = null

    override fun initView() {
        setListener()
        viewModel.getDoctors()
    }

    override fun observeLiveData() {
        viewModel.doctorsLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is DataHolder.Success -> {
                    hideLoading()
                    binding.llContainer.visible()
                    it.data.doctors?.let { docList ->
                        viewModel.doctorList.value = docList
                        doctorListAdapter = DoctorsAdapter(docList)
                        binding.rvDoctors.adapter = doctorListAdapter
                        binding.rvDoctors.addMarginDivider()
                        doctorListAdapter.itemClickListener = ::navigateToDoctorDetail
                    }
                }
                is DataHolder.Loading -> {
                    binding.llContainer.gone()
                    showLoading()
                }
                else -> {
                    binding.llContainer.gone()
                    hideLoading()
                    Toast.makeText(context, resources.getString(R.string.opps), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        //gender filter
        viewModel.selectedGender.observe(viewLifecycleOwner, {
            if (this::doctorListAdapter.isInitialized) {
                if (it.type != Gender.DEFAULT.type) {
                    val filterDoctors: List<Doctor>? =
                        if (viewModel.filterText.value.isNullOrEmpty())
                            viewModel.doctorList.value?.filter { f -> f.gender == it.type }
                        else {
                            viewModel.doctorList.value?.filter { f ->
                                f.gender == it.type && f.full_name.toLowerCase()
                                    .contains(viewModel.filterText.value!!)
                            }
                        }
                    doctorListAdapter.updateData(filterDoctors)
                    viewModel.isUserIncluded.value = !filterDoctors.isNullOrEmpty()
                } else {
                    val filterDoctors: List<Doctor>? =
                        if (viewModel.filterText.value.isNullOrEmpty())
                            viewModel.doctorList.value
                        else
                            viewModel.doctorList.value?.filter { f ->
                                f.full_name.toLowerCase().contains(viewModel.filterText.value!!)
                            }

                    doctorListAdapter.updateData(filterDoctors)
                    viewModel.isUserIncluded.value = !filterDoctors.isNullOrEmpty()
                }
            }
        })

        //search filter
        viewModel.filterText.observe(viewLifecycleOwner, {
            if (this::doctorListAdapter.isInitialized) {
                val filterDoctors: List<Doctor>? = viewModel.doctorList.value?.filter { f ->
                    if (viewModel.selectedGender.value != Gender.DEFAULT) {
                        f.gender == viewModel.selectedGender.value?.type && f.full_name.toLowerCase()
                            .contains(it)
                    } else {
                        f.full_name.toLowerCase().contains(it)
                    }
                }
                doctorListAdapter.updateData(filterDoctors)
                viewModel.isUserIncluded.value = !filterDoctors.isNullOrEmpty()
            }
        })
    }

    private fun setListener() {
        searchItemTextListener = BehaviorSubject.create()
        searchItemTextListener?.let {
            it.debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { text: String? -> viewModel.filterText.value = text }
        }

        binding.edtFilter.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                searchItemTextListener?.onNext(binding.edtFilter.text.toString().trim { it <= ' ' })
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun navigateToDoctorDetail(doctor: Doctor) = MainActivity.addFragment(
        DoctorDetailFragment.newInstance(doctor), parentFragmentManager
    )

    override fun getSafeArgs() {
        //no-op now
    }

}