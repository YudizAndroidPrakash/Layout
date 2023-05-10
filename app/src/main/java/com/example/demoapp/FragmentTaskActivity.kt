package com.example.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.demoapp.Fragment.FamilyFragment
import com.example.demoapp.Fragment.FriendFragment
import com.example.demoapp.Fragment.HomeFragment
import com.example.demoapp.Fragment.HomeFragment2
import com.google.android.material.bottomnavigation.BottomNavigationView


class FragmentTaskActivity : AppCompatActivity(),TransferData {
    private lateinit var btnNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_task)
        btnNavigation = findViewById(R.id.bottomNavigation)
        replaceFragment(HomeFragment())



        btnNavigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.btnHome -> replaceFragment(HomeFragment())
                R.id.btnFamily -> replaceFragment(FamilyFragment())
                R.id.btnFriends -> replaceFragment(FriendFragment())
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
    }

    override fun sendData(data: String) {
        Toast.makeText(applicationContext, data, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStack()
        }
        else{
            finish()
        }
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        if(HomeFragment2.backPressed)
//    }
}