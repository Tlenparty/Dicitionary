package com.geekbrains.dictionary.helpers.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


fun Context.isOnline(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}