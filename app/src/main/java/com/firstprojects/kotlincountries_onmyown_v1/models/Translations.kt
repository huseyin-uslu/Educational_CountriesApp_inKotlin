package com.firstprojects.kotlincountries_onmyown_v1.models


import com.google.gson.annotations.SerializedName

data class Translations(
    @SerializedName("br")
    val br: String?,
    @SerializedName("de")
    val de: String?,
    @SerializedName("es")
    val es: String?,
    @SerializedName("fa")
    val fa: String?,
    @SerializedName("fr")
    val fr: String?,
    @SerializedName("hr")
    val hr: String?,
    @SerializedName("it")
    val `it`: String?,
    @SerializedName("ja")
    val ja: String?,
    @SerializedName("nl")
    val nl: String?,
    @SerializedName("pt")
    val pt: String?
)