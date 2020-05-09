package com.nds.droidtv.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
        val series: Series = totalSeries[position]
        Glide.with(context)
            .load(series.posterUri)
            .placeholder(R.drawable.episode_thumbnail_placeholder)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.binding.imgItem)
        holder.binding.imgItem.setOnClickListener {
            clickEventListener.onItemClick(position)
        }
        if (series.nextPlayableEpisodeId != 0) holder.binding.playNext.setImageResource(R.drawable.poster_play_button) else holder.binding.playNext.visibility = View.GONE
        if (series.isFavorite) holder.binding.favoriteIcon.setImageResource(R.drawable.poster_favorite_overlay) else holder.binding.playNext.visibility = View.GONE
        when (series.numberOfRecordings) {
            0 -> holder.binding.recordingsCount.setImageResource(R.drawable.poster_recording_count_0)
            1 -> holder.binding.recordingsCount.setImageResource(R.drawable.poster_recording_count_1)
            2 -> holder.binding.recordingsCount.setImageResource(R.drawable.poster_recording_count_2)
            3 -> holder.binding.recordingsCount.setImageResource(R.drawable.poster_recording_count_3)
            4 -> holder.binding.recordingsCount.setImageResource(R.drawable.poster_recording_count_4)
            5 -> holder.binding.recordingsCount.setImageResource(R.drawable.poster_recording_count_5)
            6 -> holder.binding.recordingsCount.setImageResource(R.drawable.poster_recording_count_6)
            7 -> holder.binding.recordingsCount.setImageResource(R.drawable.poster_recording_count_7)
            8 -> holder.binding.recordingsCount.setImageResource(R.drawable.poster_recording_count_8)
            9 -> holder.binding.recordingsCount.setImageResource(R.drawable.poster_recording_count_9)
            else -> holder.binding.recordingsCount.setImageResource(R.drawable.poster_recording_count_10)
        }
        when (series.numberOfPlayableRecordings) {
            0 -> holder.binding.playableRecordingsCount.setImageResource(R.drawable.poster_playable_count_0)
            1 -> holder.binding.playableRecordingsCount.setImageResource(R.drawable.poster_playable_count_1)
            2 -> holder.binding.playableRecordingsCount.setImageResource(R.drawable.poster_playable_count_2)
            3 -> holder.binding.playableRecordingsCount.setImageResource(R.drawable.poster_playable_count_3)
            4 -> holder.binding.playableRecordingsCount.setImageResource(R.drawable.poster_playable_count_4)
            5 -> holder.binding.playableRecordingsCount.setImageResource(R.drawable.poster_playable_count_5)
            6 -> holder.binding.playableRecordingsCount.setImageResource(R.drawable.poster_playable_count_6)
            7 -> holder.binding.playableRecordingsCount.setImageResource(R.drawable.poster_playable_count_7)
            8 -> holder.binding.playableRecordingsCount.setImageResource(R.drawable.poster_playable_count_8)
            9 -> holder.binding.playableRecordingsCount.setImageResource(R.drawable.poster_playable_count_9)
            10 -> holder.binding.playableRecordingsCount.setImageResource(R.drawable.poster_playable_count_10)
        }
    }
}