package com.example.demoapp.Fragment

import android.app.SearchManager.OnCancelListener
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.demoapp.FragmentTaskActivity
import com.example.demoapp.R
import com.example.demoapp.TransferData

class HomeFragment : Fragment() {

    private  lateinit var btnNextHome : Button
    private  lateinit var  btnSendData : Button
    private  lateinit var  etSendData : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var return_inflater = inflater.inflate(R.layout.fragment_home, container, false)
        btnNextHome = return_inflater.findViewById(R.id.btnNewHome)
        btnSendData = return_inflater.findViewById(R.id.btnSendData)
        etSendData = return_inflater.findViewById(R.id.etText)
        var transferDataTo = activity as TransferData
        btnSendData.setOnClickListener {
            transferDataTo.sendData(etSendData.text.toString())
        }
        btnNextHome.setOnClickListener {
                activity?.supportFragmentManager!!.beginTransaction().replace(R.id.frame_layout,HomeFragment2()).addToBackStack("homeFragment").commit()


        }
        return return_inflater
    }
}