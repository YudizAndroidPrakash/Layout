package com.example.demoapp.roomdatabase.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.example.demoapp.R
import com.example.demoapp.roomdatabase.AppRoomDatabase
import kotlinx.coroutines.*


class LoginActivity : AppCompatActivity() {

    private lateinit var etUserEmail: EditText
    private lateinit var etUserPassword: EditText



    //    private lateinit var registrationViewModel: UserRegistrationViewModel
    private lateinit var userShareRequest: SharedPreferences
    private lateinit var btnLoginHome: Button


    //   private late init var database: AppRoomDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUserEmail = findViewById(R.id.et_lg_user_email)
        etUserPassword = findViewById(R.id.et_lg_user_pass)
        btnLoginHome = findViewById(R.id.btn_login_home)

//        registrationViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
//        )[UserRegistrationViewModel::class.java]


        btnLoginHome.setOnClickListener {

            if (etUserEmail.text.toString().isEmpty() && etUserPassword.text.toString().isEmpty()) {
                alert("Incorrect Email or password")
            } else if (etUserEmail.text.toString().isEmpty()) {
                alert("Incorrect Email")
                etUserEmail.requestFocus()
            } else if (etUserPassword.text.toString().isEmpty()) {
                alert("Incorrect Password")
                etUserPassword.requestFocus()
                etUserPassword.text.clear()
            } else {


                GlobalScope.launch(Dispatchers.IO) {
                    val database : AppRoomDatabase = AppRoomDatabase.getObject(applicationContext)
                    val data = database.userDao()
                        .getUserDetail(etUserEmail.text.toString(), etUserPassword.text.toString())
                    Log.d("user", data.toString())

                    if (data.isNotEmpty()) {
                        userShareRequest =
                            getSharedPreferences("user_details", Context.MODE_PRIVATE)
                        val editor = userShareRequest.edit()
                        editor.putLong("userid", data[0].id)
                        editor.putString("username", data[0].Name)
                        editor.putString("userEmail", data[0].Email)
                        editor.putString("userPassword", data[0].Password)
                        editor.apply()
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        runOnUiThread {
                            alert("Please check your email and  password")
                            etUserEmail.requestFocus()
                            etUserPassword.text.clear()
                        }

                    }
                }

            }
        }

        findViewById<TextView>(R.id.tv_sign_up).setOnClickListener {
            startActivity(Intent(this, UserRegistrationActivity::class.java))
            finish()
        }


    }

    private fun alert(msg: String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.alert_all_field_required)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.create()
        dialog.show()
        dialog.findViewById<TextView>(R.id.tv_required_filed_msg).text = msg

        dialog.findViewById<Button>(R.id.btn_ok_msg).apply {
            text = getString(R.string.ok)
            setOnClickListener {
                dialog.dismiss()
            }
        }
    }


}