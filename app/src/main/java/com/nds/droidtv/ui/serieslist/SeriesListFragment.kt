package com.nds.droidtv.ui.serieslist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.nds.droidtv.adapters.SeriesDialogAdapter
import com.nds.droidtv.adapters.SeriesListAdapter
import com.nds.droidtv.common.views.SeriesListDialog
import com.nds.droidtv.interfaces.TopBarClickListener
import com.nds.droidtv.models.Series
import com.nds.droidtv2.R
import com.nds.droidtv2.databinding.SeriesListPortraitFragmentBinding
import java.util.ArrayList

class SeriesListFragment : Fragment() , SeriesListViewModelCallback, SeriesDialogAdapter.DialogItemClickListener {
    private lateinit var binding: SeriesListPortraitFragmentBinding
    private lateinit var seriesAdapter: SeriesListAdapter
    private lateinit var dialog: SeriesListDialog

    companion object {
        fun newInstance() = SeriesListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.series_list_portrait_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SeriesListViewModel(this).loadData()
        init()
    }

    private fun init() {
        val topbar = binding.topBar
        (activity as AppCompatActivity).setSupportActionBar(topbar.toolbar)
        setHasOptionsMenu(true)
        topbar.topBarClickListener = TopBarClickHandler()
        topbar.isLeftMenuShow = true
        topbar.title.text = getString(R.string.series_list_top_bar_title)
        topbar.isTitleShow = true

        binding.rvSeriesList.apply {
            layoutManager = GridLayoutManager(activity, 4)
            adapter = seriesAdapter
        }
    }

    inner class TopBarClickHandler : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            when (value) {
                getString(R.string.top_bar_left_menu) -> Toast.makeText(activity, "left menu clicked!", Toast.LENGTH_SHORT).show()
                getString(R.string.top_bar_right_menu) -> Toast.makeText(activity, "right menu clicked!", Toast.LENGTH_SHORT).show()
                getString(R.string.top_bar_right_btn) -> Toast.makeText(activity, "right button clicked!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDataFetched(series: ArrayList<Series>) {
        seriesAdapter = SeriesListAdapter(series)
        seriesAdapter.setEventListener(object : SeriesListAdapter.EventListener {
            override fun onItemClick(pos: Int) {
                Toast.makeText(activity, "item $pos clicked.", Toast.LENGTH_SHORT).show()
            }
            override fun onItemLongPress(pos: Int) {
                val items = ArrayList<String>()
                items.add(getString(R.string.more_information))
                if (series[pos].nextPlayableEpisodeId != 0) items.add(getString(R.string.play_next_episode))
                items.add(getString(R.string.record_new_episodes))
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

    override fun clickOnDialogItem(data: String) {
        Toast.makeText(activity, "item $data clicked.", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_popup_menu, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.castToTv -> {
                Toast.makeText(context, "Cast to Tv clicked.", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.castingBtn -> {
                Toast.makeText(context, "Casting button clicked.", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.preferences -> {
                Toast.makeText(context, "Preferences clicked.", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
