package com.epam.counter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

typealias NetworkListener = (isEnabled:Boolean)->Unit

class NetworkMonitor(private var context: Context) {

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
  lateinit var networkListener:NetworkListener

    fun register() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager.activeNetwork == null) {
                networkListener(false)
            }

            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    networkListener(false)
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    when {
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) -> {
                            networkListener(true)
                        }
                        else -> {
                            networkListener(false)
                        }
                    }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val intentFilter = IntentFilter()
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
            context.registerReceiver(networkChangeReceiver, intentFilter)
        }
    }

    fun unregister() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } else {
            context.unregisterReceiver(networkChangeReceiver)
        }
    }

    private val networkChangeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo

            if (activeNetworkInfo != null) {
                networkListener(true)
            } else {
                networkListener(false)
            }
        }
    }
}


