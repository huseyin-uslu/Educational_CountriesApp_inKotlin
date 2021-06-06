package com.firstprojects.kotlincountries_onmyown_v1.adapter

import android.view.View
import com.firstprojects.kotlincountries_onmyown_v1.models.CountriesMainModelItem

interface ItemOnClickListener {
    fun onItemClickListener(view : View,country : CountriesMainModelItem)
}