package com.example.demoapp.multithreading

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.demoapp.R


//Use an async task to execute a loop and update the counter with each iteration.
//The counter value is to be displayed in the text view.
//When the count value reaches 10, cancel the async task.
//Display a toast after the async task is completed executing.

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
        val tvUpdateCounter: TextView = findViewById(R.id.tvCounterUpdate)
        findViewById<Button>(R.id.btnStartCounter).setOnClickListener {
            AsyncDemo(tvUpdateCounter, this).execute(120, 20, 30)
        }
    }
}

class AsyncDemo(var counterUpdate: TextView, var context: Context) : AsyncTask<Int, Int, Int>() {

    //work run on program
    override fun doInBackground(vararg p0: Int?): Int {
        var count = 0

        for (i in 0..p0[0]!!) {
            count = i
            publishProgress(count)
            if (count == 10) {
                isCancelled
                break
            }
        }
        return count
    }
//result after the completed task
    override fun onPostExecute(result: Int?) {
//        Toast.makeText(context, "hello $result", Toast.LENGTH_SHORT).show()
        "Completed  $result".also { counterUpdate.text = it }
    }
//values on progress
    override fun onProgressUpdate(vararg values: Int?) {
//        counterUpdate.text = values[0].toString()
        Toast.makeText(context, "toast ${values[0]}", Toast.LENGTH_SHORT).show()
    }


}


