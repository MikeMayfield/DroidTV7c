package com.nds.droidtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nds.droidtv.ui.serieslist.SeriesListFragment
import com.nds.droidtv2.R

/**
 * Main (first) activity
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SeriesListFragment.newInstance())
                .commitNow()
        }
    }

}