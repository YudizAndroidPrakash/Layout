package com.example.demoapp.roomdatabase.ui

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.R

class UserRegistrationActivity : AppCompatActivity() {
    private lateinit var registrationViewModel: UserRegistrationViewModel
    private lateinit var etUserName: EditText
    private lateinit var etUserEmail: EditText
    private lateinit var etUserMobile: EditText
    private lateinit var etUserPassword: EditText
    private lateinit var etUserConfirmPassword: EditText
    private lateinit var btnSubmitRegister: Button
    private lateinit var tvSignIn : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        etUserName = findViewById(R.id.et_user_name)
        etUserEmail = findViewById(R.id.et_user_email)
        etUserMobile = findViewById(R.id.et_user_mobile)
        etUserPassword = findViewById(R.id.et_user_password)
        etUserConfirmPassword = findViewById(R.id.et_user_conf_password)
        btnSubmitRegister = findViewById(R.id.btn_save_user_data)
        tvSignIn = findViewById(R.id.tv_sign_in)

        registrationViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserRegistrationViewModel::class.java]



        btnSubmitRegister.setOnClickListener {
            if(validationData())  {
                registrationViewModel.insertUser(
                    UserRegistration(
                        0,
                        etUserName.text.toString(),
                        etUserEmail.text.toString(),
                        etUserMobile.text.toString().toLong(),
                        etUserPassword.text.toString()
                    )
                )

                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
        }
        tvSignIn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }



    private fun validationData()  : Boolean{
        etUserName = findViewById(R.id.et_user_name)
        etUserEmail = findViewById(R.id.et_user_email)
        etUserMobile = findViewById(R.id.et_user_mobile)
        etUserPassword = findViewById(R.id.et_user_password)
        etUserConfirmPassword = findViewById(R.id.et_user_conf_password)

        if(etUserName.text.isEmpty() && etUserEmail.text.isEmpty() && etUserMobile.text.isEmpty()
            && etUserPassword.text.isEmpty() && etUserConfirmPassword.text.isEmpty()){
//            alert("Please fill all Details....")
            Toast.makeText(applicationContext, "Hello", Toast.LENGTH_SHORT).show()
            
        }else if(etUserName.text.isEmpty()) {
            alert("Please fill all Details....")
            etUserName.focusable
        }else if(etUserEmail.text.isEmpty() || android.util.Patterns.EMAIL_ADDRESS.matcher(etUserEmail.text.toString()).matches()) {
            alert("Please check your email....")
            etUserEmail.focusable
        }else if(etUserMobile.text.isEmpty() || etUserMobile.text.toString().length == 10 ) {
            alert("Please check your mobile number....")
            etUserMobile.focusable
        }else if(etUserPassword.text.isEmpty() || etUserPassword.text.toString().length > 8) {
            alert("password must be more than 8 character....")
            etUserPassword.focusable
        }else if(etUserConfirmPassword.text.isEmpty()) {
            alert("confirm password and password must be same....")
            etUserConfirmPassword.focusable
        }else {
            return true
        }
        return false
    }




    private fun alert(msg: String) {
        val dialog = Dialog(this).apply {
            setContentView(R.layout.alert_all_field_required)
            window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        dialog.create()
        dialog.show()
        dialog.findViewById<TextView>(R.id.tv_alert_dialog_title).text = msg
        dialog.findViewById<Button>(R.id.btn_ok_msg).setOnClickListener {
            dialog.dismiss()
        }

    }









}