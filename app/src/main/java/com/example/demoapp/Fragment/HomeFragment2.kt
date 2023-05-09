package com.example.demoapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import com.example.demoapp.R


class HomeFragment2 : Fragment() {
    private lateinit var  btnBack : Button

    private  val fragmentTask = HomeFragment()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val return_inflater = inflater.inflate(R.layout.fragment_home2, container, false)
        btnBack= return_inflater.findViewById(R.id.btnSendData)
        btnBack.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction().replace(R.id.frame_layout,HomeFragment()).commit()
        }
        return  return_inflater
    }

}