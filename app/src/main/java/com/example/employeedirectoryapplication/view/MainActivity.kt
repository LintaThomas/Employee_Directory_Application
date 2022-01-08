package com.example.employeedirectoryapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeedirectoryapplication.Response
import com.example.employeedirectoryapplication.databinding.ActivityMainBinding
import com.example.employeedirectoryapplication.util.EmployeeListAdapter
import com.example.employeedirectoryapplication.viewModel.ActivityViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ActivityViewModel
    private lateinit var adapter: EmployeeListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setEmployeeAdapter()
        viewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        viewModel.makeApiCall()
        viewModel.getEmployeeListObserver().observe(this, Observer<Response> {
            if (it != null) {
                adapter.employeeList = it
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setEmployeeAdapter() {
        adapter = EmployeeListAdapter()
        binding.rvEmployeeList.adapter = adapter
        binding.rvEmployeeList.layoutManager = LinearLayoutManager(this)

    }
}