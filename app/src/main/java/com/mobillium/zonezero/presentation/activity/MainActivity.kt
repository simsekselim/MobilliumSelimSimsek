package com.mobillium.zonezero.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.mobillium.zonezero.R
import com.mobillium.zonezero.common.extensions.gone
import com.mobillium.zonezero.common.extensions.visible
import com.mobillium.zonezero.presentation.list.DoctorListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewLoading: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(DoctorListFragment(), supportFragmentManager)
        viewLoading = findViewById(R.id.viewLoading)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            supportFragmentManager.popBackStack()
        else finish()
    }

    fun showLoading() = viewLoading.visible()
    fun hideLoading() = viewLoading.gone()

    companion object {
        fun addFragment(baseFragment: Fragment,
                        supportFragmentManager: FragmentManager,
                        addToBackStack: Boolean = true) {
            val tran = supportFragmentManager.beginTransaction()
            tran.add(R.id.container, baseFragment)
            if (addToBackStack)
                tran.addToBackStack(baseFragment.javaClass.simpleName)
            tran.commit()
        }
    }
}