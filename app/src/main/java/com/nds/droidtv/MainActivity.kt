package com.nds.droidtv

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nds.droidtv.interfaces.TopBarClickListener
import com.nds.droidtv.ui.serieslist.SeriesListFragment
import com.nds.droidtv2.R
import com.nds.droidtv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SeriesListFragment.newInstance())
                .commitNow()
        }
    }

    private fun initViews() {
        val topbar = binding.topBar
        setSupportActionBar(topbar.toolbar)
        topbar.topBarClickListener = TopBarClickHandler()
        topbar.isLeftMenuShow = true
        topbar.title.text = getString(R.string.series_list_top_bar_title)
        topbar.isTitleShow = true
    }

    inner class TopBarClickHandler : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            when (value) {
                getString(R.string.top_bar_left_menu) -> Toast.makeText(this@MainActivity, "left menu clicked!", Toast.LENGTH_SHORT).show()
                getString(R.string.top_bar_right_menu) -> Toast.makeText(this@MainActivity, "right menu clicked!", Toast.LENGTH_SHORT).show()
                getString(R.string.top_bar_right_btn) -> Toast.makeText(this@MainActivity, "right button clicked!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_popup_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.castToTv -> {
                Toast.makeText(this, "Cast to Tv clicked.", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.castingBtn -> {
                Toast.makeText(this, "Casting button clicked.", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.preferences -> {
                Toast.makeText(this, "Preferences clicked.", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
