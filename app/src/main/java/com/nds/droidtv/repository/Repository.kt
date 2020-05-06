package com.nds.droidtv.repository

import android.content.Context
import com.nds.droidtv.models.*


/**
 * Main repository for database and network data
 */
object Repository {
//    private var applicationContext: Context? = null

    //Public properties
    val searchContext = SearchContext()  //Current search context
    val deviceContext = DeviceContext()  //Current device hardware context

    /**
     * Find a series by ID
     *
     * @param seriesId ID of the series to find
     */
    fun findSeries(seriesId: Int) : Series {
        return Series(1, "Series not found")  //TODO: Implement
    }

    /**
     * Update series information for changes to one or more properties
     *
     * @param series Series instance with changes
     */
    fun updateSeries(series: Series) {
        //TODO: Implement
    }

    /**
     * Find an Episode by ID
     *
     * @param episodeId ID of the series to find
     */
    fun findEpisode(episodeId: Int) : Episode {
        return Episode(1, 1, 0, 0, title = "Episode not found")  //TODO: Implement
    }

    /**
     * Update episode information for changes to one or more properties
     *
     * @param episode Episode instance with changes
     */
    fun updateEpisode(episode: Episode) {
        //TODO: Implement
    }

//    /**
//     * Override invoke operator to support singleton constructor with parameter
//     */
//    operator fun invoke(context: Context): Repository {
//        this.applicationContext = context.applicationContext
//        return this
//    }
//
//    /**
//     * Default constructor for normal use as singleton
//     */
//    operator fun invoke(): Repository {
//        if (this.applicationContext == null) {  //This guarantees applicationContext != null for all normal paths
//            throw IllegalArgumentException("Attempt to access singleton when not initialized. Should use Repository(context).method")
//        } else {
//            return this
//        }
//    }
}
