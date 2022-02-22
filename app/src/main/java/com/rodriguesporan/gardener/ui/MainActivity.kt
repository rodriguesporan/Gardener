package com.rodriguesporan.gardener.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.rodriguesporan.gardener.R

class MainActivity : AppCompatActivity() {

    private val tabLayout by lazy { findViewById<TabLayout>(R.id.tab_layout) }
    private val tvContent by lazy { findViewById<TextView>(R.id.text_view_content) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                setTextToContentTextViewByTabPosition(tab?.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setTextToContentTextViewByTabPosition(tabPosition: Int?) {
        tvContent.text = when (tabPosition) {
            0 -> resources.getString(R.string.my_garden)
            1 -> resources.getString(R.string.forest)
            else -> resources.getString(R.string.main_activity)
        }
    }
}