package com.geekbrains.dictionary.helpers.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.geekbrains.dictionary.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder


fun Context.isOnline(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}


//отображение окна с сообщением
fun Context.showMessage(
    title: String = this.getString(R.string.text_info_error_title),
    message: String
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(R.string.button_close, null)
        .setCancelable(false)
        .create()
        .show()
}