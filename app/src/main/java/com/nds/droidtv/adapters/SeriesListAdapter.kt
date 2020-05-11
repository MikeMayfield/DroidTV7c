package com.nds.droidtv.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nds.droidtv.database.MockSeriesData
import com.nds.droidtv.models.Series
import com.nds.droidtv2.R
import com.nds.droidtv2.databinding.ItemSeriesListBinding
import java.util.ArrayList

class SeriesListAdapter(private val context: Context) : RecyclerView.Adapter<SeriesListAdapter.MyViewHolder>() {

    private lateinit var clickEventListener: EventListener
    private var totalSeries: ArrayList<Series> = MockSeriesData.seriesData()

    inner class MyViewHolder(internal var binding: ItemSeriesListBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface EventListener {
        fun onItemClick(pos: Int)
    }

    fun setEventListener(eventListener: EventListener) {
        this.clickEventListener = eventListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemSeriesListBinding>(
            inflater,
            R.layout.item_series_list, parent, false
        )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return totalSeries.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val seriesObj: Series = totalSeries[position]
        val seriesPoster = holder.binding.imgItem

        seriesPoster.placeholderUrl = seriesObj.posterPlaceholderUri
        seriesPoster.posterUrl = seriesObj.posterUri
        seriesPoster.isFavorite = seriesObj.isFavorite
        seriesPoster.isPlayable = seriesObj.nextPlayableEpisodeId != 0
        seriesPoster.nextPlayableEpisodeId = seriesObj.nextPlayableEpisodeId
        seriesPoster.numberOfRecordings = seriesObj.numberOfRecordings
        seriesPoster.numberOfPlayableRecordings = seriesObj.numberOfPlayableRecordings

        seriesPoster.setOnClickListener {
            clickEventListener.onItemClick(position)
        }
    }
}