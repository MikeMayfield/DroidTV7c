package com.nds.droidtv.models

/**
 * Definition of an episode within a season
 */
data class Episode(
    val episodeId: Int,  //Unique ID of the episode
    val seasonNum: Int,  //Season number
    val episodeNum: Int,  //Episode number within season
    val title: String = "TBD",  //Full title of episode
    val description: String = "",  //Full description of episode
    var recordingStatus: RecordingStatus = RecordingStatus.DONT_RECORD,  //Status of recording request
    var playingStatus: PlayingStatus = PlayingStatus.IDLE,  //Status of player, if playing
    var downloadPriority: Int = 0,  //Download priority relative to other pending downloads
    var watchedPct: Int = 0,  //Estimated percentage watched
    val firstAiredDate: String = "Unknown",  //Date the episode first aired, in mmm dd, yyyy format
    val ratingId: Int = 0,  //ID of the ratings icon
    val durationSecs: Int = 0,  //Length of episode, in seconds
    val thumbnailUrl: String = ""  //URL of the episode thumbnail
) {

    /**
     * Status of episode recording
     */
    enum class RecordingStatus (status: Int) {
        DONT_RECORD (0),  //Not recorded or recording
        QUEUED_TO_RECORD (1),  //Queued for recording, but not yet recording
        RECORDING_IN_PROGRESS (2),  //Recording is currently in progress
        RECORDED (3)  //Recorded and available for download
    }

    /**
     * Status of player or casting of this episode
     */
    enum class PlayingStatus {
        IDLE,  //Not playing
        PLAYING,  //Playing
        PAUSED  //Paused, fast-forwarding, or rewinding
    }
}