package com.mobillium.zonezero.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.zonezero.databinding.ItemRowDoctorBinding
import com.mobillium.zonezero.domain.model.Doctor

class DoctorsAdapter(var doctors: List<Doctor>) : RecyclerView.Adapter<DoctorViewHolder>() {

    var itemClickListener: (Doctor) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        DoctorViewHolder(
            ItemRowDoctorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        doctors[position]?.let {
            holder.bind(it, itemClickListener)
        }
    }

    override fun getItemCount(): Int = doctors.size

    fun updateData(filterDoctors: List<Doctor>?){
        filterDoctors?.let {
            this.doctors = it
            this.notifyDataSetChanged()
        }
    }
}

class DoctorViewHolder(private val binding: ItemRowDoctorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Doctor,
             clickListener: (Doctor) -> Unit) {
        binding.doctor = item
        binding.executePendingBindings()
        binding.root.setOnClickListener { clickListener(item) }
    }
}