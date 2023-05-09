package com.example.demoapp

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class AlertDialogActivity : AppCompatActivity() {

    private lateinit var tvFromDate: TextView
    private lateinit var tvToDate: TextView
    private lateinit var tvSelected: TextView
    private  lateinit var  btnGetTheData : Button
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog)

        btnGetTheData = findViewById(R.id.btnGetTheData)
        tvFromDate = findViewById(R.id.tvFromDate)
        tvToDate = findViewById(R.id.tvToDate)
        tvSelected= findViewById(R.id.tvTime)

        val date = Calendar.getInstance()
        btnGetTheData.setOnClickListener {
            val dialog = Dialog(this)
          val dialogView =  dialog.setContentView(R.layout.custom_alert_dialog)
            window.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)

//            val dialogView = layoutInflater.inflate(R.layout.custom_alert_dialog, null)

            val fromDate = dialog.findViewById<EditText>(R.id.etFromDate)
            var toDate = dialog.findViewById<EditText>(R.id.etToDate)
            val todayTime =dialog.findViewById<EditText>(R.id.etTime)
            val positiveButton =dialog.findViewById<Button>(R.id.btnSubmit)
            val negativeButton =dialog.findViewById<Button>(R.id.btnCancel)
            dialog.show()

            var Cyear : Int? = null
                var CMonth : Int? = null
            var CDayOfMonth : Int? = null
            //Assign value to textView
            fromDate.setOnClickListener {
              val selected =  DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        fromDate.setText("$dayOfMonth/${month + 1}/$year")
                            Cyear = year
                            CMonth = month
                            CDayOfMonth = dayOfMonth

                        },
                    date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH)
                )
                selected.datePicker.minDate = date.timeInMillis
                selected.show()
            }
            toDate.setOnClickListener {
                DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                      toDate.setText("$dayOfMonth/${month + 1}/$year")
                    },

//                            date.get(Calendar.YEAR),
//                    date.get(Calendar.MONTH),
//                    date.get(Calendar.DAY_OF_MONTH)
                Cyear!!,CMonth!!,CDayOfMonth!!
                ).show()
            }
            todayTime.setOnClickListener {
                TimePickerDialog(
                    this,
                    TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                        todayTime.setText("$hourOfDay : $minute")

//                        val todayTimeSelect = "$hourOfDay : $minute"
//                        todayTime.setText(todayTimeSelect)
//                        .setText(todayTimeSelect)
                    }, date.get(Calendar.HOUR), date.get(Calendar.MINUTE), false
                ).show()
            }
            positiveButton.setOnClickListener {
                    var h = checkValidation(fromDate,toDate,todayTime)
                Toast.makeText(this@AlertDialogActivity, "$h", Toast.LENGTH_SHORT).show()

            }
            negativeButton.setOnClickListener {
                dialog.dismiss()
            }
        }
    }


    private fun checkValidation(fromDate: EditText?, toDate: EditText?, todayTime: EditText?): Boolean {
        val dateFormat = SimpleDateFormat("ddMMyyyy")


         if(fromDate?.text.toString().isEmpty() && toDate?.text.toString().isEmpty() && todayTime?.text.toString().isEmpty()){
               errorMessage("please fill the field")
        }else if(fromDate?.text.toString().isEmpty()) {
            errorMessage("please select the from date")
        }else if(toDate?.text.toString().isEmpty()){
            errorMessage("please select the to date")
        }else if(todayTime?.text.toString().isEmpty()){
            errorMessage("please select the time")
        }else if(toDate!!.text.toString() > fromDate!!.text.toString()){
            errorMessage("Please check the date")
        }
         else {
            return  true
        }
        return false
    }
    
    private  fun errorMessage(msg : String){
        AlertDialog.Builder(this).setTitle("Error").setMessage(msg).setPositiveButton("Ok",null).show()
    }


}