package com.example.demoapp.roomdatabase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
    private lateinit var btnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        etUserName = findViewById(R.id.et_user_name)
        etUserEmail = findViewById(R.id.et_user_email)
        etUserMobile = findViewById(R.id.et_user_mobile)
        etUserPassword = findViewById(R.id.et_user_password)
        etUserConfirmPassword = findViewById(R.id.et_user_conf_password)
        btnSubmitRegister = findViewById(R.id.btn_save_user_data)
        btnLogin = findViewById(R.id.btn_login_screen)


        registrationViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserRegistrationViewModel::class.java]



        btnSubmitRegister.setOnClickListener {
            registrationViewModel.insertUser(
                UserRegistration(
                    0,
                    etUserName.text.toString(),
                    etUserEmail.text.toString(),
                    etUserMobile.text.toString().toLong(),
                    etUserPassword.text.toString()
                )
            )
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }




    }
}