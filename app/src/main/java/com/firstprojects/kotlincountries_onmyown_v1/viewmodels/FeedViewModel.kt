package com.firstprojects.kotlincountries_onmyown_v1.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firstprojects.kotlincountries_onmyown_v1.models.CountryMainArray
import com.firstprojects.kotlincountries_onmyown_v1.service.Countries_service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel(){

    private val disposable = CompositeDisposable()
    private val service    = Countries_service()

    val countrylistlive = MutableLiveData<CountryMainArray>()
    val loadinglive     = MutableLiveData<Int>()
    val errorlive       = MutableLiveData<Int>()


    fun refreshData() {
        getDataFromApi()
    }

    private fun getDataFromApi() {
        loadinglive.value = 1
        errorlive.value   = 0
        disposable.add(service.getData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<CountryMainArray>(){
                override fun onSuccess(t: CountryMainArray) {
                   showCountries(t)
                }

                override fun onError(e: Throwable) {
                    println("error = ${e.localizedMessage}")
                    errorlive.value = 1
                    loadinglive.value = 0
                }

            })
        )
    }

    private fun showCountries(countrylist : CountryMainArray) {
        countrylistlive.value = countrylist
        errorlive.value       = 0
        loadinglive.value     = 0

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }



}