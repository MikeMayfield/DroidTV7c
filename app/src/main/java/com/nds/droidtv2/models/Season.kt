package com.nds.droidtv2.models

/**
 * Data about a season of episodes for a series
 */
data class Season(
    val seasonNum: Int,  //Season number
    val episodes: ArrayList<Episode>  //List of episodes in season, sorted by ascending episode number
)