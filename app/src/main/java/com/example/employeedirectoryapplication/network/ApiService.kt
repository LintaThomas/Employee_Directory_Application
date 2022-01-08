package com.example.employeedirectoryapplication.network

import com.example.employeedirectoryapplication.Response
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("v2/5d565297300000680030a986/")
    fun getEmployeeListFromApi(): Observable<Response>
}