package com.nds.droidtv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nds.droidtv.models.Series
import com.nds.droidtv2.R
import com.nds.droidtv2.databinding.ItemSeriesListBinding
import java.util.ArrayList

class SeriesListAdapter : RecyclerView.Adapter<SeriesListAdapter.MyViewHolder>() {

    lateinit var clickEventListener: EventListener
    var series: ArrayList<Series> = ArrayList()

    init {
        addSeriesToList()
    }

    inner class MyViewHolder(internal var binding: ItemSeriesListBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface EventListener {
        fun onItemClick(pos: Int)
    }

    fun setEventListener(eventListener: EventListener) {
        this.clickEventListener = eventListener
    }

    fun addSeriesToList() {
        series.add(Series(1,
            "sample1",
            "description1",
            posterId = R.drawable.poster_1))
        series.add(Series(1,
            "sample1",
            "description1",
            posterId = R.drawable.poster_2))
        series.add(Series(1,
            "sample1",
            "description1",
            posterId = R.drawable.poster_3))
        series.add(Series(1,
            "sample1",
            "description1",
            posterId = R.drawable.poster_4))
        series.add(Series(1,
            "sample1",
            "description1",
            posterId = R.drawable.poster_5))
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
        return series.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.imgItem.setImageResource(series.get(position).posterId)
        holder.binding.imgItem.setOnClickListener {
            clickEventListener.onItemClick(position)
        }
    }
}