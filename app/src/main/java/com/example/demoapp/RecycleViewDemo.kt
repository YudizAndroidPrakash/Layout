package com.example.demoapp

import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleViewDemo : AppCompatActivity() {
    private lateinit var rv_Demo: RecyclerView
    private lateinit var tvDatanotFound: TextView
    private lateinit var taskData: ArrayList<DataHoldPerson>
    private lateinit var btnDeleteAll: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view_demo)
        taskData = ArrayList()
        var adapter = TaskAdapter(taskData)
        addData(taskData)
        bindOnAdapater(adapter)
        btnDeleteAll = findViewById(R.id.btnDeleteAll)
        var sizeOfList = taskData.size

        btnDeleteAll.setOnClickListener(object : android.view.View.OnClickListener {
            override fun onClick(p0: View?) {
                var j = taskData.filter { it.checked }
                for (i in j){
                    var k = taskData.indexOf(i)
                    taskData.removeAt(k)
                    adapter.notifyItemRemoved(k)
                    adapter.notifyItemRangeChanged(k,taskData.size)
                }
            }
        })

    }


    private fun bindOnAdapater(adapter: TaskAdapter) {
        rv_Demo = findViewById(R.id.rv_task_data)
        rv_Demo.adapter = adapter
    }

    private fun addData(taskData: ArrayList<DataHoldPerson>) {

        taskData.add(DataHoldPerson("prakash1", "parmar@gmail.com", 1234567890))
        taskData.add(DataHoldPerson("prakash2", "parmar@gmail.com", 1234567890))
        taskData.add(DataHoldPerson("prakash3", "parmar@gmail.com", 1234567890))
        taskData.add(DataHoldPerson("prakash4", "parmar@gmail.com", 1234567890))
        taskData.add(DataHoldPerson("prakash5", "parmar@gmail.com", 1234567890))
        taskData.add(DataHoldPerson("prakash6", "parmar@gmail.com", 1234567890))
        taskData.add(DataHoldPerson("prakash7", "parmar@gmail.com", 1234567890))
    }
}


