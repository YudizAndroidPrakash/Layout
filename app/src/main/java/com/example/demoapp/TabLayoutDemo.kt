package com.example.demoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class TabLayoutDemo : AppCompatActivity() {
    private var tabLayoutDemo : TabLayout? = null
    var vpLayoutDemo : ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout_demo)
        tabLayoutDemo = findViewById(R.id.tlDemo)
        vpLayoutDemo = findViewById(R.id.vpLayout)
        val iconArray = intArrayOf(R.drawable.ic_home_24,
            R.drawable.ic_mobile_friendly_24,
            R.drawable.ic_mobile_screen_share_24)
//        tabLayoutDemo?.addTab(tabLayoutDemo!!.newTab().setText(R.string.homeTab).setIcon(R.drawable.ic_home_24))
//        tabLayoutDemo?.addTab(tabLayoutDemo!!.newTab().setText(R.string.friendsTab).setIcon(R.drawable.ic_mobile_friendly_24))
//        tabLayoutDemo?.addTab(tabLayoutDemo!!.newTab().setText(R.string.familyTab).setIcon(R.drawable.ic_mobile_screen_share_24))

        tabLayoutDemo!!.addTab(tabLayoutDemo!!.newTab().setText(R.string.homeTab))
        tabLayoutDemo!!.addTab(tabLayoutDemo!!.newTab().setText(R.string.friendsTab))
        tabLayoutDemo!!.addTab(tabLayoutDemo!!.newTab().setText(R.string.familyTab))



        val adapter = MyTlFragementAdapter(this,supportFragmentManager,tabLayoutDemo!!.tabCount)
        vpLayoutDemo!!.adapter = adapter

        vpLayoutDemo!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayoutDemo))

        tabLayoutDemo?.tabCount
        tabLayoutDemo!!.addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener {

            @SuppressLint("SuspiciousIndentation")
            override fun onTabSelected(tab: TabLayout.Tab?) {

                vpLayoutDemo!!.currentItem = tab!!.position
                    displayIcon(tab,iconArray)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon = null
              }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun displayIcon( tab: TabLayout.Tab, iconArray: IntArray) {
                tab.setIcon(iconArray[tab.position])
    }


}