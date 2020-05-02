package com.nds.droidtv.ui.serieslist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nds.droidtv2.R

/**
 * Fragment for SeriesListView
 */
class SeriesListFragment : Fragment() {

    companion object {
        fun newInstance() = SeriesListFragment()
    }

    private lateinit var viewModel: SeriesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.series_list_portrait_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SeriesListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
