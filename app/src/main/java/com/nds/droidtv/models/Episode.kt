package com.nds.droidtv.models

/**
 * Definition of an episode within a season
 */
data class Episode(
    val episodeId: Int,  //Unique ID of the episode
    val seriesId: Int,  //Associated series containing the episode
    val seasonNum: Int,  //Season number
    val episodeNum: Int,  //Episode number within season
    val title: String = "TBD",  //Full title of episode
    val description: String = "",  //Full description of episode
    var recordingStatus: RecordingStatus = RecordingStatus.DONT_RECORD,  //Status of recording request
    var playingStatus: PlayingStatus = PlayingStatus.NOT_PLAYABLE,  //Status of player, if playing
    var downloadPriority: Int = 0,  //Download priority relative to other pending downloads. 0=Don't download
    var watchedPct: Int = 0,  //Estimated percentage watched
    val firstAiredDate: String = "Unknown",  //Date the episode first aired, in mmm dd, yyyy format
    val ratingId: Rating = Rating.TV_PG,  //ID of the ratings icon
    val durationSecs: Int = 0,  //Length of episode, in seconds
    val thumbnailUrl: String = ""  //URL of the episode thumbnail
) {

    val shouldDownloadWhenRecorded  //Flag: The episode should download when recording completes
        get() = downloadPriority != 0

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
        NOT_PLAYABLE,  //Not downloaded and so not playable
        IDLE,  //Not playing
        PLAYING,  //Playing
        PAUSED,  //Paused, fast-forwarding, or rewinding
        PENDING_REMOVAL  //Remove the recording and change to NOT_PLAYABLE ASAP
    }

    /**
     * Series rating IDs
     */
    enum class Rating (rating: Int) {
        TV_G (0),
        TV_14 (1),
        TV_MA (2),
        TV_PG (3),
        TV_Y (4),
        TV_Y7 (5)
    }
}