package com.nds.droidtv.models

class DownloadList {
    val pendingDownloads = arrayListOf<Episode>()  //List of all pending downloads

    /**
     * Queue an episode to be downloaded
     */
    fun queueRecording(episode: Episode) {
        TODO()
    }

    /**
     * Remove a download request, cancel its download, delete any partially downloaded file
     */
    fun dequeueRecording(episode: Episode) {
        TODO()
    }
}