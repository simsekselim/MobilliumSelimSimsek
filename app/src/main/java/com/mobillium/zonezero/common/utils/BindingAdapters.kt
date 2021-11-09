package com.mobillium.zonezero.common.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mobillium.zonezero.R
import com.mobillium.zonezero.common.extensions.glide

@BindingAdapter("url")
fun ImageView.load(url: String?) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_user)
        .error(R.drawable.ic_user)
        .fallback(R.drawable.ic_user)
        .fitCenter()
        .into(this)
}

@BindingAdapter("isPremiumAction")
fun TextView.isPremiumAction(isPremium: Boolean) {
    this.text = if (isPremium)
        this.context.resources.getString(R.string.meeting)
    else
        this.context.resources.getString(R.string.buypremium)
}