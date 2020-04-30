package com.nds.droidtv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nds.droidtv2.ui.serieslist.SeriesListFragment

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
