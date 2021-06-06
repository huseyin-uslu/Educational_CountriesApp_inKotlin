package com.firstprojects.kotlincountries_onmyown_v1.service

import com.firstprojects.kotlincountries_onmyown_v1.models.CountriesMainModelItem
import com.firstprojects.kotlincountries_onmyown_v1.models.CountryMainArray
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Countries_service {



    private var BASE_URL = "https://restcountries.eu/"
    private var api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(country_api::class.java)

    fun getData() : Single<CountryMainArray> {
        return api.getData()
    }
}