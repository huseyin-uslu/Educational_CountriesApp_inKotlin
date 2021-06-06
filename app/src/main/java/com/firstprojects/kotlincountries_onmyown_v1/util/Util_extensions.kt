package com.firstprojects.kotlincountries_onmyown_v1.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.firstprojects.kotlincountries_onmyown_v1.R



fun ImageView.downloadImageFromUrlbyCoil(url : String? , progressBar: CircularProgressDrawable?) {

    val imageLoader = ImageLoader(context).newBuilder()
        .componentRegistry { add(SvgDecoder(context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        .placeholder(progressBar)
        .error(R.mipmap.ic_launcher_round)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)

}

fun progressBarForWaitingForDownloading (context : Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadImage")
fun imagedownloadBinding(view : ImageView,url : String) {
    view.downloadImageFromUrlbyCoil(url, progressBarForWaitingForDownloading(view.context))
}