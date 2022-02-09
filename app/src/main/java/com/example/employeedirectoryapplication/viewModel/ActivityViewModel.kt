package com.example.employeedirectoryapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeedirectoryapplication.Response
import com.example.employeedirectoryapplication.ResponseItem
import com.example.employeedirectoryapplication.network.ApiService
import com.example.employeedirectoryapplication.network.RetroInstance
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ActivityViewModel : ViewModel() {
    var employeeList: MutableLiveData<Array<ResponseItem>> = MutableLiveData()

    fun getEmployeeListObserver(): MutableLiveData<Array<ResponseItem>> {
        return employeeList
    }

    fun makeApiCall() {

        val retroInstance = RetroInstance.getRetrofitInstance().create(ApiService::class.java)
        retroInstance.getEmployeeListFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getEmployeeListObserverRx())
    }

    private fun getEmployeeListObserverRx(): Observer<Array<ResponseItem>> {
        return object : Observer<Array<ResponseItem>> {
            override fun onComplete() {

            }

            override fun onError(e: Throwable) {
                Log.e("Check", e.message.toString())
                employeeList.postValue(null)
            }

            override fun onNext(t: Array<ResponseItem>) {
                employeeList.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
            }
        }
    }
}