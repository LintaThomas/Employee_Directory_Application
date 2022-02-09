package com.example.employeedirectoryapplication.util

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.employeedirectoryapplication.Response


import com.example.employeedirectoryapplication.R
import com.example.employeedirectoryapplication.ResponseItem
import com.example.employeedirectoryapplication.view.EmployeeDetailsActivity

class EmployeeListAdapter : RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>() {
     var employeeList : Array<ResponseItem>? = null

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvEmployeeName : TextView =itemView.findViewById(R.id.tv_employees_Name)
        var tvCompanyName : TextView =itemView.findViewById(R.id.tv_company_name)
        var ivEmployeePhoto : ImageView =itemView.findViewById(R.id.iv_employees_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_employee_list, parent, false))
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        if(employeeList?.get(position)!=null) {
            holder.tvEmployeeName.text =
                employeeList!![position].name
            holder.tvCompanyName.text =
                employeeList!![position].company?.name ?:""
            val url = employeeList!![position].profileImage
            Glide.with(holder.ivEmployeePhoto)
                .load(url)
                .circleCrop()
                .into(holder.ivEmployeePhoto)

        }

    }

    override fun getItemCount(): Int{
        return employeeList?.size ?: 0

    }
}