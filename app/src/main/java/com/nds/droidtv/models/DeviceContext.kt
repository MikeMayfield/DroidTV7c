package com.nds.droidtv.models

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Information about device hardware
 */
class DeviceContext() {
    val mutableNetworkingIsAvailable = MutableLiveData<Boolean>(networkingIsAvailable())

    //Public properties
    var casting: Casting = Casting()  //Casting support
    val networkingIsAvailable: LiveData<Boolean>  //Networking is currently available
        get() = mutableNetworkingIsAvailable
    var allowCellularNetwork: Boolean = false  //Allow use of cellular network, including roaming
        set(value) {
            field = value
            mutableNetworkingIsAvailable.value = networkingIsAvailable()
        }
    var sdCardPath: String = "TODO"  //Path to the SD card to use for storing recordings
        private set
    var playerHasFocus: Boolean = false  //The internal video player currently has focus
        private set  //TODO Update this property in lifecycle events

    init {
        //TODO Implement ctor as needed
    }

    private fun networkingIsAvailable() : Boolean {
        return true  //TODO: Implement this using the ConnectivityManager.NetworkCallback API
    }
}