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
        //TODO: On background thread: Load saved settings from database. Perform search
    }

    /**
     * Search categories
     */
    enum class SearchCategory {
        ALL,  //All series (Primetime + Classic)
        CLASSIC,  //Classic TV (non-primetime)
        DVR,  //Recorded episodes on cloud DVR (My DVR)
        PLAYABLE,  //Playable episodes (Now Playing)
        PRIMETIME  //Primetime (non-classic) episodes
    }

    @VisibleForTesting fun saveSettings() {
        //TODO: Save settings to database on background thread
    }

    /**
     * Queue a new search request and perform a search on a background thread. On completion,
     * update the search results in seriesList and notify the observable data change.
     */
    @VisibleForTesting fun performSearch(category: SearchCategory,
                                             searchText: String,
                                             favoritesFilterIsEnabled: Boolean,
                                             playableFilterIsEnabled: Boolean) {
        //TODO: Replace this mocked behavior with actual search using database
        val searchResult = mockSeriesList
            .filter { mockSearchFilter(it, category, searchText, favoritesFilterIsEnabled, playableFilterIsEnabled) }
            .sortedBy { it.sortableTitle }
        mutableSeriesList.postValue(searchResult)
    }

    //TODO: Remove this after implementation
    private fun mockSearchFilter(series: Series,
                                 category: SearchCategory,
                                 searchText: String,
                                 favoritesFilterIsEnabled: Boolean,
                                 playableFilterIsEnabled: Boolean) : Boolean {
        var result = true  //Series is included unless it fails any our our exclusion tests

        when (category) {
            SearchCategory.ALL -> {
                result = result && !(favoritesFilterIsEnabled && !series.isFavorite)
                result = result && !(playableFilterIsEnabled && series.numberOfPlayableRecordings == 0)
            }
            SearchCategory.CLASSIC -> {
                result = result && !(favoritesFilterIsEnabled && !series.isFavorite)
                result = result && !(playableFilterIsEnabled && series.numberOfPlayableRecordings == 0)
                result = result && !series.isPrimetime
            }
            SearchCategory.DVR -> {
                result = result && !(favoritesFilterIsEnabled && !series.isFavorite)
                result = result && !(playableFilterIsEnabled && series.numberOfPlayableRecordings == 0)
                result = result && series.numberOfRecordings > 0
            }
            SearchCategory.PLAYABLE -> {
                result = result && !(favoritesFilterIsEnabled && !series.isFavorite)
                result = result && series.numberOfPlayableRecordings > 0
            }
            SearchCategory.PRIMETIME -> {
                result = result && !(favoritesFilterIsEnabled && !series.isFavorite)
                result = result && !(playableFilterIsEnabled && series.numberOfPlayableRecordings == 0)
                result = result && series.isPrimetime
            }
        }

        return result
    }

    //TODO: Remove this after implementation
    private val mockSeriesList = arrayListOf(
        Series(3, "Series 3", isFavorite = true, newEpisodesShouldBeRecorded = true, numberOfRecordings = 10, numberOfPlayableRecordings = 2),
        Series(1, "Series 1", isFavorite = true),
        Series(2, "Series 2", isFavorite = true, isPrimetime = false),
        Series(4, "Series 4", isFavorite = false),
        Series(5, "Series 5", isFavorite = false)
    )
}