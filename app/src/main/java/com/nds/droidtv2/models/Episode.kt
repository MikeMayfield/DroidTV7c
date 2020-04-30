package com.nds.droidtv2.models

/**
 * Definition of an episode within a season
 */
data class Episode(
    val episodeId: Long,  //Unique ID of the episode
    val seasonNum: Int,  //Season number
    val episodeNum: Int,  //Episode number within season
    val title: String,  //Full title of episode
    val description: String,  //Full description of episode
    var recordingStatus: RecordingStatus,  //Status of recording request
    var downloadPriority: Int,  //Download priority relative to other pending downloads
    var watchedPct: Int,  //Estimated percentage watched
    val firstAiredDate: String,  //Date the episode first aired, in mmm dd, yyyy format
    val ratingId: Int,  //ID of the ratings icon
    val durationSecs: Int,  //Length of episode, in seconds
    val thumbnailUrl: String  //URL of the episode thumbnail
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
}