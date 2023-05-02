package com.example.demoapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.demoapp.Fragment.FamilyFragment
import com.example.demoapp.Fragment.FriendFragment
import com.example.demoapp.Fragment.HomeFragment

class MyTlFragementAdapter(var context: Context, fm: FragmentManager, var tabCounts: Int) :
    FragmentPagerAdapter(fm) {
    override fun getCount() = tabCounts

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return HomeFragment()
            }
            1 -> {
                return FriendFragment()

            }
            2 -> {
                return FamilyFragment()
            }
            else -> {
               return  HomeFragment()
            }
        }
    }
}