package com.nds.droidtv.common.views

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nds.droidtv2.R
import kotlinx.android.synthetic.main.series_list_dialog.*

class SeriesListDialog(context: Context, private var dialogAdapter: RecyclerView.Adapter<*>) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.series_list_dialog)

        rvSeriesListDialog.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dialogAdapter
        }

    }
}