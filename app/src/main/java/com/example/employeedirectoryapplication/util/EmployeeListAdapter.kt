package com.example.employeedirectoryapplication.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.employeedirectoryapplication.Response


import com.example.employeedirectoryapplication.R

class EmployeeListAdapter : RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>() {
     var employeeList : Response? = null

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
        if(employeeList?.response !=null) {
            holder.tvEmployeeName.text =
                employeeList!!.response?.get(position)?.name
            holder.tvCompanyName.text
            employeeList!!.response?.get(position)?.company
            val url =  employeeList!!.response?.get(position)?.profileImage
            Glide.with(holder.ivEmployeePhoto)
                .load(url)
                .circleCrop()
                .into(holder.ivEmployeePhoto)

        }
    }

    override fun getItemCount(): Int{
        return employeeList?.response?.size ?: 0

    }
}