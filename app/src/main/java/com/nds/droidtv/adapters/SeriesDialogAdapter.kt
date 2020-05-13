package com.nds.droidtv.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nds.droidtv2.R
import com.nds.droidtv2.databinding.ItemDialogListBinding

class SeriesDialogAdapter(
    private val mDataset: ArrayList<String>,
    internal var recyclerViewItemClickListener: DialogItemClickListener
) : RecyclerView.Adapter<SeriesDialogAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDialogListBinding>(
            inflater,
            R.layout.item_dialog_list, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.textView.text = mDataset[i]
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }


    inner class ViewHolder(binding: ItemDialogListBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var textView: TextView = binding.textDialogItem
        init {
            textView.setOnClickListener(this)
        }
        override fun onClick(v: View) {
            recyclerViewItemClickListener.clickOnDialogItem(mDataset[this.adapterPosition])
        }
    }

    interface DialogItemClickListener {
        fun clickOnDialogItem(data: String)
    }
}