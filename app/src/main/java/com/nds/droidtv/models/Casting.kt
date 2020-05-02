package com.nds.droidtv.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Casting controller
 */
class Casting {
    private var mutableCastingIsEnabled = MutableLiveData(false)
    private var playingEpisode: Episode? = null   //Currently playing episode, if any

    //Public properties
    val castingIsEnabled: LiveData<Boolean>  //Flag: Casting is currently enabled
        get() = mutableCastingIsEnabled
    val castingDevices = MutableLiveData<ArrayList<CastingDevice>>()
    var selectedCastingDevice: CastingDevice? = null
        private set (value) {
            field = value
            mutableCastingIsEnabled.value = (value != null)
        }

    /**
     * Select a device for casting
     *
     * @param device Device to cast to. NULL to disable casting
     */
    fun selectDevice(device: CastingDevice?) {
        TODO()
    }

    /**
     * Change the caster player status
     */
    fun setPlayingStatus(playingStatus: Episode.PlayingStatus) {
        playingEpisode?.playingStatus = playingStatus
    }

    /**
     * Change the percent watched value for the current episode
     */
    fun setWatchedPct(watchedPct: Int) {
        playingEpisode?.watchedPct = watchedPct
    }

    /**
     * Play (cast) an episode to the currently selected casting device
     *
     * @param episode Episode to cast
     */
    fun play(episode: Episode) {
        TODO()
    }
}