package com.nds.droidtv.ui.serieslist

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.nds.droidtv.database.MockSeriesData
import com.nds.droidtv.models.SearchContext
import com.nds.droidtv.models.Series
import com.nds.droidtv.repository.Repository

class SeriesListViewModel(private val callback: SeriesListViewModelCallback) : ViewModel() {

    private val seriesList = MockSeriesData.seriesData()

    fun loadData() {
        callback.onDataFetched(seriesList)
    }

    @VisibleForTesting
    fun search(searchText: String, categoryNum: SearchContext.SearchCategory, favoriteFilterIsEnabled: Boolean, playableFilterIsEnabled: Boolean): ArrayList<Series> {
        return if (!favoriteFilterIsEnabled && !playableFilterIsEnabled)
            ArrayList(Repository.searchContext.getSeriesListFromDb(categoryNum, searchText))
        else
            ArrayList(Repository.searchContext.performSearch(categoryNum, searchText, favoriteFilterIsEnabled, playableFilterIsEnabled))
    }
}

interface SeriesListViewModelCallback {
    fun onDataFetched(series: ArrayList<Series>)
}
