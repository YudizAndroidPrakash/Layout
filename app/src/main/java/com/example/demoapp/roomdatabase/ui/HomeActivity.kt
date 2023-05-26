package com.example.demoapp.roomdatabase.ui


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.roomdatabase.ITaskRVAdapater
import com.example.demoapp.roomdatabase.TaskAdapterRoomDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeActivity : AppCompatActivity(), ITaskRVAdapater {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var rvTask: RecyclerView
    private lateinit var fbAddTask: FloatingActionButton
    private lateinit var btnLogout: Button
    private lateinit var getUserData: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnLogout = findViewById(R.id.btn_logout)

        fbAddTask = findViewById(R.id.fb_add_task)

        taskViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TaskViewModel::class.java]


        fbAddTask.setOnClickListener {
            addTaskAlertDialog()
        }

        btnLogout.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_logout_button)
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            dialog.findViewById<Button>(R.id.btn_confirm_logout).setOnClickListener {
                getUserData = getSharedPreferences("user_details", Context.MODE_PRIVATE)
                val editor = getUserData.edit()
                editor.clear().apply()
                dialog.dismiss()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }


            dialog.findViewById<Button>(R.id.btn_cancel_logout).setOnClickListener {
                dialog.dismiss()
            }
        }


        val getUserData = getSharedPreferences("user_details", Context.MODE_PRIVATE)
        rvTask = findViewById(R.id.rv_list_task)
        val adapter = TaskAdapterRoomDatabase(this, this)
        rvTask.adapter = adapter


        taskViewModel.allUserTask(getUserData.getLong("userid", 0L)).observe(this)
        { list ->
            list?.let {
                adapter.updateList(it)
            }
        }

    }

    override fun onItemClick(task: Task) {
        val dialog = Dialog(this).apply {
            setContentView(R.layout.custom_alert_dialog_room_database)
            window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        dialog.create()
        dialog.show()
        val taskHeader = dialog.findViewById<TextView>(R.id.tv_alert_dialog_title)
        val taskTitle = dialog.findViewById<EditText>(R.id.et_task_title)
        val taskDescription = dialog.findViewById<EditText>(R.id.et_task_description)

        val updateButton = dialog.findViewById<Button>(R.id.btn_add_task)
        val deleteButton = dialog.findViewById<Button>(R.id.btn_cancel_task)

        taskHeader.text = getString(R.string.add_task)

        taskTitle.setText(task.taskTitle)
        taskDescription.setText(task.taskDescription)


        updateButton.text = getString(R.string.update_task)
        deleteButton.text = getString(R.string.delete_task)

        deleteButton.setOnClickListener {
            taskViewModel.deleteTask(task)
            dialog.dismiss()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }

        updateButton.setOnClickListener {
            taskViewModel.updateTask(
                taskTitle.text.toString(),
                taskDescription.text.toString(),
                task.taskId.toLong()
            )
            dialog.dismiss()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addTaskAlertDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_alert_dialog_room_database)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.create()
        dialog.show()
        val taskHeader = dialog.findViewById<TextView>(R.id.tv_alert_dialog_title)
        val taskTitle = dialog.findViewById<EditText>(R.id.et_task_title)
        val taskDescription = dialog.findViewById<EditText>(R.id.et_task_description)

        val alertSubmitButton = dialog.findViewById<Button>(R.id.btn_add_task)
        val cancelTaskButton = dialog.findViewById<Button>(R.id.btn_cancel_task)

        taskHeader.hint = getString(R.string.add_task)
        taskTitle.hint = getString(R.string.task_title)
        taskDescription.hint = getString(R.string.task_description)
        alertSubmitButton.text = getString(R.string.submit_task)
        cancelTaskButton.text = getString(R.string.cancel_task)

        cancelTaskButton.setOnClickListener {
            dialog.dismiss()
        }


        alertSubmitButton.setOnClickListener {
            val getUserData = getSharedPreferences("user_details", Context.MODE_PRIVATE)
            taskViewModel.insertTask(
                Task(
                    0,
                    taskTitle.text.toString(),
                    taskDescription.text.toString(),
                    getUserData.getLong("userid", 0L)
                )
            )
            dialog.dismiss()
            Toast.makeText(this, "Task Inserted Successfully", Toast.LENGTH_SHORT).show()
        }

    }

}