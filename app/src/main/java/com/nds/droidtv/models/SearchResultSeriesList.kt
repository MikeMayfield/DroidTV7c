package com.nds.droidtv.models

/**
 * List of Series returned by the most recent search request
 */
data class SearchResultSeriesList(
    val seriesList: ArrayList<Series>  //List of series returned by most recent search request
)