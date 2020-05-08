package com.nds.droidtv.models

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nds.droidtv.database.MockSeriesData

/**
 * Search parameters
 */
class SearchContext {
    @VisibleForTesting val mockSeriesList = MockSeriesData().seriesData()  //TODO Remove when no longer mocking database data

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
        val searchResult = getSeriesListFromDb(category, searchText)
            .filter { includeOnlySeriesThatMeetFilters(it, category, favoritesFilterIsEnabled, playableFilterIsEnabled) }
            .sortedBy { it.sortableTitle }
        mutableSeriesList.postValue(searchResult)
    }

    /**
     * Search database for all Series in category
     */
    @VisibleForTesting fun getSeriesListFromDb(category: SearchCategory, searchText: String) : List<Series> {
        //TODO: Replace this mocked behavior with actual search
        if (searchText.isNullOrBlank()) {
            return mockSeriesList
        } else {
            val searchTokens = searchText.trim().split(Regex("\\s"))
            return mockSeriesList.filter {
                var includeInList = false
                for (token in searchTokens) {
                    if (it.title.contains(token, true)) {
                        includeInList = true
                        break
                    }
                }
                includeInList
            }
        }
    }

    /**
     * Filter Series list by favorites and playable filters. Note that playable filter is implied for PLAYABLE category
     */
    private fun includeOnlySeriesThatMeetFilters(series: Series,
                                                 category: SearchCategory,
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
}