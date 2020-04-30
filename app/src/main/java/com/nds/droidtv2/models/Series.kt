package com.nds.droidtv2.models

/**
 * Data about an individual series
 */
 data class Series(
    val seriesId: Long,  //Unique ID of this series
    val title: String,  //Full title of the series
    val sortableTitle: String = title,  //Sortable version of the series title, defaults to normal title
    val description: String,  //Full description of the series
    var isFavorite: Boolean = false,  //Flag: Marked by the user as one of their favorites
    var newEpisodesShouldBeRecorded: Boolean = false,  //Flag: Automatically record new episodes for series
    var newRecordingsShouldBeDownloaded: Boolean = false,  //Flag: Automatically download new episodes
    var nextPlayableEpisodeId: Long = 0L,  //ID for next episode to play; 0=None
    var numberOfRecordings: Int,  //Number of episodes recorded or pending recording
    var numberOfPlayableRecordings: Int,  //Number of recorded episodes downloaded and ready to play
    val posterId: Long = 0L,  //Unique ID of the poster to display for the series. Identifies both the low and high res versions
    var currentSeasonIdx: Int = 0,  //Index in the seasons list of the season to display
    val seasons: ArrayList<Season>,  //List of seasons, in ascending season number order
    val imdbId: Long = 0L  //ID of the series in IMDB; 0=None
    )