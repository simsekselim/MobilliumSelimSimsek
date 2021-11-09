package com.mobillium.zonezero.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobillium.zonezero.R
import com.mobillium.zonezero.common.extensions.addListeners
import com.mobillium.zonezero.common.extensions.gone
import com.mobillium.zonezero.common.extensions.visible
import com.mobillium.zonezero.common.utils.NetworkHelper
import com.mobillium.zonezero.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var networkHelper: NetworkHelper
    private val startWithLottie: Boolean = true
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAnimation()
    }

    private fun navigateToMain(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun checkInternet(){
        if(networkHelper.isNetworkConnected()){
            navigateToMain()
        }else{
            showNoInternet()
        }
    }

    private fun showNoInternet(){
        Toast.makeText(this, resources.getString(R.string.message_check_your_internet), Toast.LENGTH_LONG).show()
    }

    private fun setAnimation(){
        if(startWithLottie){
            binding.lottieView.addListeners(
                onStart = { },
                onEnd =  { checkInternet() })
        }else{
            binding.lottieView.gone()
            checkInternet()
        }
    }
}