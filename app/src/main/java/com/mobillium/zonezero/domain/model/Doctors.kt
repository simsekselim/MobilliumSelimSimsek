package com.mobillium.zonezero.domain.model

import java.io.Serializable

data class Doctors(
    var doctors: List<Doctor>?
)

data class Doctor(
    var full_name: String,
    var gender: String,
    var image: Image,
    var user_status: String) : Serializable

data class Image(
    var url: String
)