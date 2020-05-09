package com.nds.droidtv.ui.serieslist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.GridLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.nds.droidtv.adapters.SeriesListAdapter
import com.nds.droidtv.interfaces.TopBarClickListener
import com.nds.droidtv2.R
import com.nds.droidtv2.databinding.SeriesListPortraitFragmentBinding

/**
 * Fragment for SeriesListView
 */
class SeriesListFragment : Fragment() {
    private lateinit var binding: SeriesListPortraitFragmentBinding
    private lateinit var adapter: SeriesListAdapter

    companion object {
        fun newInstance() = SeriesListFragment()
    }

    private lateinit var viewModel: SeriesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.series_list_portrait_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SeriesListViewModel::class.java)
        init()
    }


    private fun init() {
        val topbar = binding.topBar
        topbar.topBarClickListener = TopBarClickHandler()
        topbar.isLeftMenuShow = true
        topbar.isRightBtnShow = true
        topbar.isRightMenuShow = true
        topbar.title.text = getString(R.string.series_list_top_bar_title)
        topbar.isTitleShow = true

        adapter = SeriesListAdapter(requireContext())
        binding.rvSeriesList.layoutManager = GridLayoutManager(activity, 4)
        binding.rvSeriesList.adapter = adapter
        adapter.setEventListener(object : SeriesListAdapter.EventListener {
            override fun onItemClick(pos: Int) {
                Toast.makeText(activity, "item $pos clicked.", Toast.LENGTH_SHORT).show()
            }

        })
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
}
