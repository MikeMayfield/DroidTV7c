package com.nds.droidtv.models

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Search parameters
 */
class SearchContext {
    private var mutableSeriesList = MutableLiveData<List<Series>>()

    //Public properties
    var category: SearchCategory = SearchCategory.PRIMETIME  //Current search category
        set (value) {
            field = value
            saveSettings()
            performSearch(this.category, this.searchText, this.favoritesFilterIsEnabled, this.playableFilterIsEnabled)
        }

    var searchText: String = ""  //Search text or "" for all series
        set (value) {
            field = value
            saveSettings()
            performSearch(this.category, this.searchText, this.favoritesFilterIsEnabled, this.playableFilterIsEnabled)
        }

    var favoritesFilterIsEnabled: Boolean = false  //Flag: Include only series marked as favorites
        set (value) {
            field = value
            saveSettings()
            performSearch(this.category, this.searchText, this.favoritesFilterIsEnabled, this.playableFilterIsEnabled)
        }

    var playableFilterIsEnabled: Boolean = false  //Flag: Include only series that have at least one playable episode
        set (value) {
            field = value
            saveSettings()
            performSearch(this.category, this.searchText, this.favoritesFilterIsEnabled, this.playableFilterIsEnabled)
        }

    val seriesList: LiveData<List<Series>>  //List of series matching the most recent search request
        get() = mutableSeriesList

    init {
        //TODO: On background thread: Load saved settings from database. Save settings and perform search after setting all properties
    }

    /**
     * Search categories
     */
    enum class SearchCategory {
        ALL,  //All series (Primetime + Classic)
        CLASSIC,  //Classic TV (non-primetime)
        DVR,  //Recorded episodes on cloud DVR (My DVR)
        PLAYABLE,  //Playable episodes (Now Playing)
        PRIMETIME,  //Primetime (non-classic) episodes
        NEW_SERIES  //Recently added series
    }

    /**
     * Save settings to database on background thread
     */
    @VisibleForTesting fun saveSettings() {
        //TODO: Implement
    }

    /**
     * Queue a new search request and perform a search on a background thread. On completion,
     * update the search results in seriesList and notify the observable data change.
     */
    @VisibleForTesting fun performSearch(category: SearchCategory,
                                             searchText: String,
                                             favoritesFilterIsEnabled: Boolean,
                                             playableFilterIsEnabled: Boolean) {
        val searchResult = searchSeries(category, searchText)
            .filter { searchFilter(it, category, searchText, favoritesFilterIsEnabled, playableFilterIsEnabled) }
            .sortedBy { it.sortableTitle }
        mutableSeriesList.postValue(searchResult)
    }

    /**
     * Search database for all Series in category
     */
    private fun searchSeries(category: SearchCategory, searchText: String) : List<Series> {
        return mockSeriesList          //TODO: Replace this mocked behavior with actual search
    }

    /**
     * Filter Series list by favorites and playable filters. Note that playable filter is implied for PLAYABLE category
     */
    private fun searchFilter(series: Series,
                             category: SearchCategory,
                             searchText: String,
                             favoritesFilterIsEnabled: Boolean,
                             playableFilterIsEnabled: Boolean) : Boolean {
        val forcedPlayableFilterIsEnabled =
            if (category == SearchCategory.PLAYABLE) true
            else playableFilterIsEnabled
        var seriesShouldBeIncluded = !(favoritesFilterIsEnabled && !series.isFavorite) &&
                !(forcedPlayableFilterIsEnabled && series.numberOfPlayableRecordings == 0)  //Series is included unless it fails our filters

        when (category) {
            SearchCategory.CLASSIC -> {
                seriesShouldBeIncluded = seriesShouldBeIncluded && !series.isPrimetime
            }
            SearchCategory.DVR -> {
                seriesShouldBeIncluded = seriesShouldBeIncluded && series.numberOfRecordings > 0
            }
            SearchCategory.PRIMETIME -> {
                seriesShouldBeIncluded = seriesShouldBeIncluded && series.isPrimetime
            }
        }

        return seriesShouldBeIncluded
    }

    //TODO: Remove this after implementation
    private val mockSeriesList = arrayListOf(
        Series(3, "Series 3", isFavorite = true, newEpisodesShouldBeRecorded = true,
            numberOfRecordings = 10, numberOfPlayableRecordings = 2,
            seasons = arrayListOf<Season>(Season(1,
                arrayListOf(
                    Episode(1, 3, 1, 1, "Series 3, Season 1, Episode 1",
                        description = "This is the description of S1E1. It is several lines long so that it needs to be ellipsized when collapsed.  It is several lines long so that it needs to be ellipsized when collapsed."),
                    Episode(2, 3, 1, 2, "Series 3, Season 1, Episode 2")
                )))),
        Series(1, "Series 1", isFavorite = true,
            seasons = arrayListOf<Season>(Season(1,
                arrayListOf(
                    Episode(1, 1, 1, 1, "Series 1, Season 1, Episode 1")
                )))),
        Series(2, "Series 2", isFavorite = true, isPrimetime = false),
        Series(4, "Series 4", isFavorite = false),
        Series(5, "Series 5", isFavorite = false)
    )
}