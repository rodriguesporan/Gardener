package com.rodriguesporan.gardener.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import com.rodriguesporan.gardener.R

class MainActivity : AppCompatActivity() {

    private val tabLayout by lazy { findViewById<TabLayout>(R.id.tab_layout) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setUpListeners()
    }

    private fun setUpListeners() {
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
        when (tabPosition) {
            1 -> popBackStackAndNavigateTo(R.id.forestFragment)
            else -> popBackStackAndNavigateTo(R.id.myGardenFragment)
        }
    }

    private fun popBackStackAndNavigateTo(resId: Int) {
        navController.also {
            it.popBackStack()
            it.navigate(resId)
        }
    }
}