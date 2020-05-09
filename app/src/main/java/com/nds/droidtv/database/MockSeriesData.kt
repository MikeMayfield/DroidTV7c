package com.nds.droidtv.database

import com.nds.droidtv.models.Episode
import com.nds.droidtv.models.Season
import com.nds.droidtv.models.Series
import java.util.ArrayList

object MockSeriesData {
    fun seriesData() : ArrayList<Series> {
        val mockSeriesList = arrayListOf(
            Series(1,
                title="NCIS",
                description = "This is the description for Series 1. It is a favorite with one season with one episode.",
                isFavorite = true,
                posterId = 10001,
                numberOfRecordings = 1,
                numberOfPlayableRecordings = 0,
                newEpisodesShouldBeRecorded = true,
                newRecordingsShouldBeDownloaded = true,
                seasons = arrayListOf<Season>(
                    Season(1,
                        arrayListOf(
                            Episode(1, 1, 1, 1,
                                "NCIS, Season 1, Episode 1",
                                "This is the description for NCIS (Series #1), season 1, episode 1",
                                recordingStatus = Episode.RecordingStatus.QUEUED_TO_RECORD
                            )
                        ))
                )),

            Series(2,
                title = "NCIS: Los Angeles",
                sortableTitle ="NCIS Los Angeles",
                description = "This is the descripition for Series 2. It is a favorite with no episodes.",
                isFavorite = true
                ),

            Series(3,
                title = "The Series 3",
                sortableTitle = "Series 3, The",
                description = "This is the description for Series 3. It is a favorite with two seasons with two and one episodes.",
                isFavorite = true,
                newEpisodesShouldBeRecorded = true,
                newRecordingsShouldBeDownloaded = true,
                numberOfRecordings = 2,
                numberOfPlayableRecordings = 1,
                seasons = arrayListOf<Season>(
                    Season(1,
                        arrayListOf(
                            Episode(2, 3, 1, 1,
                                "Series 3, Season 1, Episode 1",
                                description = "This is the description of S1E1. It is several lines long so that it needs to be ellipsized when collapsed.  It is several lines long so that it needs to be ellipsized when collapsed.",
                                recordingStatus = Episode.RecordingStatus.RECORDED,
                                downloadPriority = 12345
                            ),

                            Episode(3, 3, 1, 2,
                                "Series 3, Season 1, Episode 2",
                                description = "This is the description of S1E2. It is several lines long so that it needs to be ellipsized when collapsed.  It is several lines long so that it needs to be ellipsized when collapsed.",
                                recordingStatus = Episode.RecordingStatus.DONT_RECORD
                            )
                        )
                    ),
                    Season(2,
                        arrayListOf(
                            Episode(4  , 3, 2, 1,
                                "Series 3, Season 2, Episode 1",
                                description = "This is the description of S2E1. It is several lines long so that it needs to be ellipsized when collapsed.  It is several lines long so that it needs to be ellipsized when collapsed.",
                                recordingStatus = Episode.RecordingStatus.RECORDED,
                                playingStatus = Episode.PlayingStatus.PAUSED
                            )
                        )
                    )
                )),

            Series(5,
                "Series 5",
                isFavorite = false,
                isPrimetime = false
            ),

            Series(4,
                "Series 4",
                isFavorite = false)
        )

        return mockSeriesList
    }
}