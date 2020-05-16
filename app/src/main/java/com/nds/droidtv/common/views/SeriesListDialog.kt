package com.nds.droidtv.common.views

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nds.droidtv2.R
import kotlinx.android.synthetic.main.view_dialog_series_list.*

class SeriesListDialog(context: Context, private var dialogAdapter: RecyclerView.Adapter<*>) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.view_dialog_series_list)

        rvSeriesListDialog.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dialogAdapter
        }

    }
}