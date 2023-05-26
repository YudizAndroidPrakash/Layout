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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.R
import kotlinx.coroutines.*
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {

    private lateinit var etUserEmail: EditText
    private lateinit var etUserPassword: EditText
    private lateinit var registrationViewModel: UserRegistrationViewModel
    private lateinit var userShareRequest : SharedPreferences
    private lateinit var btnLoginHomw : Button


    //   private late init var database: AppRoomDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUserEmail = findViewById(R.id.et_lg_user_email)
        etUserPassword = findViewById(R.id.et_lg_user_pass)
        btnLoginHomw = findViewById(R.id.btn_login_home)

        registrationViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserRegistrationViewModel::class.java]


        btnLoginHomw.setOnClickListener {
            Toast.makeText(this, "jhgjyfhfhgfghfghfgh", Toast.LENGTH_SHORT).show()
            if (etUserEmail.text.toString().isEmpty() && etUserPassword.text.toString().isEmpty()) {
                alert("Please check your email and password")

            } else if(android.util.Patterns.EMAIL_ADDRESS.matcher(etUserEmail.text.toString()).matches()){
                alert("Please check your email")
                etUserEmail.focusable
            }else if(etUserPassword.text.toString().length > 8){
                alert("Please check your password")
            }
            else {
                val database = AppRoomDatabase.getObject(applicationContext)

                GlobalScope.launch(Dispatchers.IO) {
                    val data = database.userDao()
                        .getUserDetail(etUserEmail.text.toString(), etUserPassword.text.toString())


                    Log.d("user", data.toString())

                    if (data.isNotEmpty()) {
                       userShareRequest = getSharedPreferences("user_details", Context.MODE_PRIVATE)
                        val editor = userShareRequest.edit()
                        editor.putLong("userid", data[0].id)
                        editor.putString("username", data[0].Name)
                        editor.putString("userEmail", data[0].Email)
                        editor.putString("userPassword", data[0].Password)
                        editor.apply()
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    } else {
                        runOnUiThread {
                            alert("User not found")
                        }

                    }

                }

            }
        }


        findViewById<TextView>(R.id.tv_sign_up).setOnClickListener {
            startActivity(Intent(this,UserRegistrationActivity::class.java))
            finish()
        }


    }

    private fun alert(msg : String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.alert_all_field_required)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.findViewById<TextView>(R.id.tv_required_filed_msg).text = msg
        dialog.findViewById<Button>(R.id.btn_ok_msg).text = getString(R.string.ok)
    }


}