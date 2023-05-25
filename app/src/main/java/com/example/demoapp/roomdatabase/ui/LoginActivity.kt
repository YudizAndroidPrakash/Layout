package com.example.demoapp.roomdatabase.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.contentcapture.DataShareRequest
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.R
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {

    private lateinit var etUserEmail: EditText
    private lateinit var etUserPassword: EditText
    private lateinit var registrationViewModel: UserRegistrationViewModel
    private var userShareRequest = getSharedPreferences("user_details",Context.MODE_PRIVATE)
    private val editor = userShareRequest.edit()

    //   private late init var database: AppRoomDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUserEmail = findViewById(R.id.et_lg_user_email)
        etUserPassword = findViewById(R.id.et_lg_user_pass)

        registrationViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserRegistrationViewModel::class.java]

//        database = Room.databaseBuilder(applicationContext
//        ,UserDatabase::class.java,"userDB"
//        ).build()

//        database = AppRoomDatabase.getObject(this)
//                findViewById<Button>(R.id.btn_display_data).setOnClickListener {
//
//
//
//                }

        findViewById<Button>(R.id.btn_login_home).setOnClickListener {
            if (etUserEmail.text.toString().isEmpty() && etUserPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter the email and password", Toast.LENGTH_SHORT)
                    .show()
            } else {

//             val one = registrationViewModel.getUserDetail(etUserEmail.text.toString(),etUserPassword.text.toString())
//
//              val data = registrationViewModel.selectedData
//                data!!.observe(this, Observer {
//                    if(it.isEmpty()){
//                        Toast.makeText(this, "no record found", Toast.LENGTH_SHORT).show()
//                    }
//                })
//                Log.d("user",data.toString())
//            }
                val database = AppRoomDatabase.getObject(applicationContext)

                GlobalScope.launch(Dispatchers.IO) {
                    val data = database.userDao()
                        .getUserDetail(etUserEmail.text.toString(), etUserPassword.text.toString())


                    Log.d("user", data.toString())

                    if (data.isNotEmpty()) {
                            saveInformation(data)
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    } else {
                        runOnUiThread {
                            alert()

                        }

//                        AlertDialog.Builder(this@LoginActivity).setTitle("Error").setMessage("user not found").setPositiveButton("Ok",null).show()

                    }

//                    if(data.isEmpty())
//                    }{
//                        Toast.makeText(applicationContext, "data not found", Toast.LENGTH_SHORT).show()
//                    }else {
//
//                    }
                }

            }
        }


//        MainScope().launch {
//            database.userDao().insertUser(
//                UserRegistration(
//                    0, "ABC", "abc@gmail.com", 1234567890, "bcdedit", "bcdedit"
//                )
//            )
//        }
//        findViewById<Button>(R.id.btn_display_data).setOnClickListener {
////            database.userDao().getUSer().observe(this) {
////                Log.d("user", it.toString())
////            }
//            MainScope().launch {
//                database.userDao().deleteUser(
//                    UserRegistration(
//                        1, "ABC", "abc@gmail.com", 1234567890, "bcdedit", "bcdedit"
//                    )
//                )
//            }
//        }


    }

    private fun alert() {
        AlertDialog.Builder(this@LoginActivity).setTitle("Error").setMessage("user not found")
            .setPositiveButton("Ok", null).show()
    }

   suspend fun  saveInformation(data : List<UserRegistration>){
       delay(1000)
       editor.putLong("userid", data[0].id)
       editor.putString("username", data[0].Name)
       editor.putString("userEmail", data[0].Email)
       editor.putString("userPassword", data[0].Password)
      editor.apply()

    }
}