package com.nds.droidtv.ui.serieslist

import androidx.lifecycle.ViewModel
import com.nds.droidtv.database.MockSeriesData
import com.nds.droidtv.models.Series
import java.util.ArrayList

class SeriesListViewModel(private val callback: SeriesListViewModelCallback) : ViewModel() {
    fun loadData() {
        callback.onDataFetched(MockSeriesData.seriesData())
    }
}

interface SeriesListViewModelCallback {
    fun onDataFetched(series: ArrayList<Series>)
}
