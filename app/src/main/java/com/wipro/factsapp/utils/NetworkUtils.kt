package com.wipro.factsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Utils class for network related operations
 */
object NetworkUtils {

    /**
     * Checks for internet connection
     *
     * @param context Context
     * @return True if internet is available, else false.
     */
    fun isNetworkConnected(context: Context): Boolean {
        var isConnected = false

        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val networks = connectivityManager.allNetworks ?: return false
            for (network in networks) {
                val nc = connectivityManager.getNetworkCapabilities(network) ?: continue

                if (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    isConnected = true
                }
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            isConnected = networkInfo.isConnected
                    && (networkInfo.type == ConnectivityManager.TYPE_WIFI
                    || networkInfo.type == ConnectivityManager.TYPE_MOBILE)
        }
        return isConnected
    }
}