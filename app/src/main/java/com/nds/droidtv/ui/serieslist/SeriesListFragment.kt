package com.nds.droidtv.ui.serieslist

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.nds.droidtv.adapters.SeriesDialogAdapter
import com.nds.droidtv.adapters.SeriesListAdapter
import com.nds.droidtv.common.views.SeriesListDialog
import com.nds.droidtv.models.SearchContext
import com.nds.droidtv.models.Series
import com.nds.droidtv2.R
import com.nds.droidtv2.databinding.FragmentSeriesListPortraitBinding
import kotlinx.android.synthetic.main.fragment_series_list_portrait.*
import kotlin.collections.ArrayList

class SeriesListFragment : Fragment() , SeriesListViewModelCallback, SeriesDialogAdapter.DialogItemClickListener {
    private lateinit var binding: FragmentSeriesListPortraitBinding
    private lateinit var seriesAdapter: SeriesListAdapter
    private lateinit var dialog: SeriesListDialog
    private var favoriteFilter: Boolean = false
    private var playableFilter: Boolean = false

    companion object {
        fun newInstance() = SeriesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_series_list_portrait, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        // Get series list from ViewModel
        SeriesListViewModel(this).loadData()
        // Init RecyclerView
        if (activity!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rvSeriesList.layoutManager = GridLayoutManager(activity, 4)
            binding.rvSeriesList.adapter = seriesAdapter
        } else {
            binding.rvSeriesList.layoutManager = GridLayoutManager(activity, 7)
            binding.rvSeriesList.adapter = seriesAdapter
        }
        // Set search views listener
        setViewListener()
    }

    override fun onDataFetched(series: ArrayList<Series>) {
        if (searchResultStatus(series)) {
            seriesAdapter = SeriesListAdapter(series)
            seriesAdapter.setEventListener(object : SeriesListAdapter.EventListener {
                override fun onItemClick(pos: Int) {
                    Toast.makeText(activity, "item $pos clicked.", Toast.LENGTH_SHORT).show()
                }

                override fun onItemLongPress(pos: Int) {
                    val items = ArrayList<String>()
                    items.add(getString(R.string.more_information))
                    if (series[pos].nextPlayableEpisodeId != 0) items.add(getString(R.string.play_next_episode))
                    if (series[pos].newEpisodesShouldBeRecorded) items.add(getString(R.string.record_new_episodes))
                    items.add(getString(R.string.record_all_episodes))
                    items.add(getString(R.string.record_new_episodes_DVR))
                    items.add(getString(R.string.record_all_episodes_DVR))
                    items.add(getString(R.string.stop_recording_new_episodes))
                    items.add(getString(R.string.stop_recording))
                    if (!series[pos].isFavorite) items.add(getString(R.string.add_to_favorites))
                    if (series[pos].isFavorite) items.add(getString(R.string.remove_from_favorites))
                    val adapter = SeriesDialogAdapter(items, this@SeriesListFragment)
                    dialog = SeriesListDialog(context!!, adapter)
                    dialog.show()
                    dialog.setCanceledOnTouchOutside(false)
                }

            })
        }
    }

    override fun clickOnDialogItem(data: String) {
        Toast.makeText(activity, "item $data clicked.", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }

    private fun setViewListener() {
        binding.searchBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                searchForSeries(s.toString(), favoriteFilter, playableFilter)
            }
        })
        binding.favoriteFilterImg.setOnClickListener {
            favoriteFilter = if (!favoriteFilter) {
                favoriteFilterImg.setImageResource(R.drawable.search_filter_favorites_enabled)
                true
            } else {
                favoriteFilterImg.setImageResource(R.drawable.search_filter_favorites_disabled)
                false
            }
            searchForSeries(binding.searchBox.text.toString(), favoriteFilter, playableFilter)
        }
        binding.playableFilterImg.setOnClickListener(View.OnClickListener {
            playableFilter = if (!playableFilter) {
                playableFilterImg.setImageResource(R.drawable.serach_filter_playable_enabled)
                true
            } else {
                playableFilterImg.setImageResource(R.drawable.serach_filter_playable_disabled)
                false
            }
            searchForSeries(binding.searchBox.text.toString(), favoriteFilter, playableFilter)
        })
    }

    private fun searchResultStatus(list: ArrayList<Series>): Boolean {
        return if (list.isEmpty()) {
            noSearchResultTxt.visibility = View.VISIBLE
            rvSeriesList.visibility = View.INVISIBLE
            false
        } else {
            noSearchResultTxt.visibility = View.INVISIBLE
            rvSeriesList.visibility = View.VISIBLE
            true
        }
    }

    @VisibleForTesting
    private fun searchForSeries(searchText: String, isFavorite: Boolean, isPlayable: Boolean) {
        val newList = SeriesListViewModel(this@SeriesListFragment).search(searchText, SearchContext.SearchCategory.PRIMETIME, isFavorite, isPlayable)
        if (searchResultStatus(newList)) seriesAdapter.updateList(newList)
    }
}
