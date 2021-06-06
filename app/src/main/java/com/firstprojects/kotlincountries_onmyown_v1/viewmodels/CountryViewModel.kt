package com.firstprojects.kotlincountries_onmyown_v1.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firstprojects.kotlincountries_onmyown_v1.models.CountriesMainModelItem
import com.firstprojects.kotlincountries_onmyown_v1.models.CountrySeriliazableModel

class CountryViewModel : ViewModel(){

    val countrymodel    = MutableLiveData<CountrySeriliazableModel>()
    val error           = MutableLiveData<Boolean>()


    fun getData(countriesmodel : CountrySeriliazableModel?) {

        if(countriesmodel != null) {
            error.value = false
            countriesmodel.let {country ->
                countrymodel.value = country
            }

        }else {
            countrymodel.value = null
            error.value = true
        }
    }
}