package com.example.demoapp.roomdatabase


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.roomdatabase.ui.Task


class TaskAdapterRoomDatabase(val context: Context, private val listener : ITaskRVAdapater) : RecyclerView.Adapter<TaskAdapterRoomDatabase.ViewHolder>(){
    val alltask = ArrayList<Task>()

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


         var taskTitle : TextView
        var  taskDescription : TextView

       init {
           taskTitle = itemView.findViewById(R.id.tv_task_title)
           taskDescription = itemView.findViewById(R.id.tv_task_description)
       }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewHolder( LayoutInflater.from(context).inflate(R.layout.task_list,parent,false))
         view.itemView.setOnClickListener {
             listener.onItemClick(alltask[view.adapterPosition])
         }
        return  view
    }
    override fun getItemCount() = alltask.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentTask = alltask[position]
        holder.taskTitle.text = currentTask.taskTitle
        holder.taskDescription.text = currentTask.taskDescription
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list : List<Task>){
        alltask.clear()
        alltask.addAll(list)
        notifyDataSetChanged()
    }
}

interface  ITaskRVAdapater{
    fun  onItemClick(task: Task)
}