package com.example.demoapp.roomdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.TaskAdapter
import com.example.demoapp.roomdatabase.ITaskRVAdapater
import com.example.demoapp.roomdatabase.TaskAdapterRoomDatabase

class HomeActivity : AppCompatActivity(), ITaskRVAdapater {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var rvTask: RecyclerView
    private lateinit var etTaskTitle: EditText
    private lateinit var edTaskDescription: EditText
    private lateinit var btnSubmitData: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        taskViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TaskViewModel::class.java)

        etTaskTitle = findViewById(R.id.ed_task_title)
        edTaskDescription = findViewById(R.id.ed_task_description)
        btnSubmitData = findViewById(R.id.btn_add_task)

        btnSubmitData.setOnClickListener {
            taskViewModel.insertTask(
                Task(0,
                    etTaskTitle.text.toString(),
                    edTaskDescription.text.toString(),
                    1
                )
            )
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show()
        }

        rvTask = findViewById(R.id.rv_list_task)
        val adapter = TaskAdapterRoomDatabase(this, this)
        rvTask.adapter = adapter

        taskViewModel.allTask.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })

    }
    override fun onItemClick(task: Task) {
        taskViewModel.deleteTask(task)
        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()
    }

}