package com.firstprojects.kotlincountries_onmyown_v1.service

import com.firstprojects.kotlincountries_onmyown_v1.models.CountriesMainModelItem
import com.firstprojects.kotlincountries_onmyown_v1.models.CountryMainArray
import io.reactivex.Single
import retrofit2.http.GET

interface country_api {

    //https://restcountries.eu/rest/v2/all

    @GET("rest/v2/all")
    fun getData() : Single<CountryMainArray>
}