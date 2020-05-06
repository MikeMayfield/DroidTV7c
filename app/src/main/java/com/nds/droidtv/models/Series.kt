package com.nds.droidtv.models

/**
 * Data about an individual series
 */
data class Series(
   val seriesId: Int,  //Unique ID of this series
   val title: String,  //Full title of the series
   val sortableTitle: String = title,  //Sortable version of the series title, defaults to normal title
   val description: String = "",  //Full description of the series
   var isFavorite: Boolean = false,  //Flag: Marked by the user as one of their favorites
   var isPrimetime: Boolean = true,  //Flag: This is a current prime time series
   var newEpisodesShouldBeRecorded: Boolean = false,  //Flag: Automatically record new episodes for series
   var newRecordingsShouldBeDownloaded: Boolean = false,  //Flag: Automatically download new episodes
   var nextPlayableEpisodeId: Int = 0,  //ID for next episode to play; 0=None
   var numberOfRecordings: Int = 0,  //Number of episodes recorded or pending recording
   var numberOfPlayableRecordings: Int = 0,  //Number of recorded episodes downloaded and ready to play
   val posterId: Int = 0,  //Unique ID of the poster to display for the series. Identifies both the low and high res versions
   var currentSeasonIdx: Int = 0,  //Index in the seasons list of the season to display
   val seasons: ArrayList<Season> = arrayListOf(),  //List of seasons, in ascending season number order
   var selectedSeasonIdx: Int = 0,  //Last saved index into the seasons collection from the UI
   val imdbId: Int = 0,  //ID of the series in IMDB; 0=None
   var relatedSeries: ArrayList<Series> = arrayListOf()  //List of related series
)