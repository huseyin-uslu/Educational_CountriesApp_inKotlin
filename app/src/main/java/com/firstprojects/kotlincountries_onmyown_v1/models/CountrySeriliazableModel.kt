package com.firstprojects.kotlincountries_onmyown_v1.models

import java.io.Serializable

class CountrySeriliazableModel(
    var countryName: String?,
    var codename   : String?,
    var capital    : String?,
    var region     : String?,
    var population : String?,
    var currency   : String?,
    var native     : String?,
    var flaglink   : String?,
) : Serializable {
}