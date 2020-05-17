package com.nds.droidtv.ui.serieslist

import androidx.lifecycle.ViewModel
import com.nds.droidtv.database.MockSeriesData
import com.nds.droidtv.models.Series
import java.util.ArrayList

class SeriesListViewModel(private val callback: SeriesListViewModelCallback) : ViewModel() {

    lateinit var searchText: String
    var favoritesFilterIsEnabled: Boolean = false
    var playableFilterIsEnabled: Boolean = false

    val seriesList = MockSeriesData.seriesData()

    fun loadData() {
        callback.onDataFetched(seriesList)
    }

    private fun search(searchText: String, favoriteFilterIsEnabled: Boolean, playableFilterIsEnabled: Boolean) {

    }
}

interface SeriesListViewModelCallback {
    fun onDataFetched(series: ArrayList<Series>)
}
