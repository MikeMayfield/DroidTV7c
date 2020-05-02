package com.nds.droidtv.repository

import android.content.Context
import com.nds.droidtv.models.DeviceContext
import com.nds.droidtv.models.SearchContext


/**
 * Main repository for database and network data
 */
object Repository {
//    private var applicationContext: Context? = null

    //Public properties
    val searchContext = SearchContext()  //Current search context
    val deviceContext = DeviceContext()  //Current device hardware context


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
