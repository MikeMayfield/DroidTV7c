package com.nds.droidtv.models

/**
 * Casting device info
 */
data class CastingDevice(
    val deviceName: String,  //Device name reported from caster device on network
    val friendlyName: String = deviceName  //Friendly (user-facing) device name
)