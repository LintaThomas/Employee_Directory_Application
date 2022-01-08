package com.example.employeedirectoryapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeedirectoryapplication.Response
import com.example.employeedirectoryapplication.network.ApiService
import com.example.employeedirectoryapplication.network.RetroInstance
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ActivityViewModel : ViewModel() {
    var employeeList: MutableLiveData<Response> = MutableLiveData()

    fun getEmployeeListObserver(): MutableLiveData<Response> {
        return employeeList
    }

    fun makeApiCall() {

        val retroInstance = RetroInstance.getRetrofitInstance().create(ApiService::class.java)
        retroInstance.getEmployeeListFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getEmployeeListObserverRx())
    }

    private fun getEmployeeListObserverRx(): Observer<Response> {
        return object : Observer<Response> {
            override fun onComplete() {

            }

            override fun onError(e: Throwable) {
                Log.e("Check", e.message.toString())
                employeeList.postValue(null)
            }

            override fun onNext(t: Response) {
                employeeList.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
            }
        }
    }
}