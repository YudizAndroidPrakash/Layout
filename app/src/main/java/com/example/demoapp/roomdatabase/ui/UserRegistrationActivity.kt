package com.example.demoapp.roomdatabase.ui

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.R
import com.example.demoapp.roomdatabase.table.UserRegistration
import com.example.demoapp.roomdatabase.viewmodel.UserRegistrationViewModel

class UserRegistrationActivity : AppCompatActivity() {
    private lateinit var registrationViewModel: UserRegistrationViewModel
    private lateinit var etUserName: EditText
    private lateinit var etUserEmail: EditText
    private lateinit var etUserMobile: EditText
    private lateinit var etUserPassword: EditText
    private lateinit var etUserConfirmPassword: EditText
    private lateinit var btnSubmitRegister: Button
    private lateinit var tvSignIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

//        etUserName = findViewById(R.id.et_user_name)
//        etUserEmail = findViewById(R.id.et_user_email)
//        etUserMobile = findViewById(R.id.et_user_mobile)
//        etUserPassword = findViewById(R.id.et_user_password)
//        etUserConfirmPassword = findViewById(R.id.et_user_conf_password)
        btnSubmitRegister = findViewById(R.id.btn_save_user_data)
        tvSignIn = findViewById(R.id.tv_sign_in)

        registrationViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserRegistrationViewModel::class.java]



        btnSubmitRegister.setOnClickListener {
            if (validationData()) {
                alertForError("Account is created successfully!!", true)
            }
        }
        tvSignIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


    private fun validationData(): Boolean {
        etUserName = findViewById(R.id.et_user_name)
        etUserEmail = findViewById(R.id.et_user_email)
        etUserMobile = findViewById(R.id.et_user_mobile)
        etUserPassword = findViewById(R.id.et_user_password)
        etUserConfirmPassword = findViewById(R.id.et_user_conf_password)

        if (etUserName.text.isEmpty() && etUserEmail.text.isEmpty() && etUserMobile.text.isEmpty()
            && etUserPassword.text.isEmpty() && etUserConfirmPassword.text.isEmpty()
        ) {
            alertForError("Please fill all Details....")
            etUserName.requestFocus()
//            Toast.makeText(applicationContext, "Hello", Toast.LENGTH_SHORT).show()

        } else if (etUserName.text.isEmpty()) {
            alertForError("Enter Your Name")
            etUserName.requestFocus()
        } else if (etUserName.text.toString().length < 3) {
            Log.d("name", etUserName.text.toString().length.toString())
            alertForError("Name Must be more than 3 character...")
            etUserName.requestFocus()
        } else if (etUserEmail.text.isEmpty()) {
            alertForError("Enter your email....")
            etUserEmail.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etUserEmail.text.toString())
                .matches()
        ) {
            alertForError("Please check your email....")
            etUserEmail.requestFocus()
        } else if (etUserMobile.text.isEmpty()) {
            alertForError("Please check your mobile number....")
            etUserMobile.requestFocus()
        } else if (etUserMobile.text.toString().length != 10) {
            alertForError("Please check your mobile number....")
            etUserMobile.requestFocus()
        } else if (etUserPassword.text.isEmpty()) {
            alertForError("Please Enter Password....")
            etUserPassword.requestFocus()
        } else if (etUserPassword.text.toString().length < 8) {
            alertForError("Password must be more than 8 character")
            etUserPassword.requestFocus()
        } else if (etUserConfirmPassword.text.toString() != etUserPassword.text.toString()) {
            Log.d("same", etUserConfirmPassword.text.equals(etUserPassword.text).toString())
            alertForError("confirm password and password must be same....")
            etUserConfirmPassword.requestFocus()
        } else {

            registrationViewModel.insertUser(
                UserRegistration(
                    0,
                    etUserName.text.toString(),
                    etUserEmail.text.toString(),
                    etUserMobile.text.toString().toLong(),
                    etUserPassword.text.toString()
                )
            )
            return true
        }
        return false
    }

    private fun alertForError(msg: String, status: Boolean = false) {
        val dialog = Dialog(this).apply {
            setContentView(R.layout.alert_all_field_required)
            window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setCanceledOnTouchOutside(false)
        }
        dialog.create()
        dialog.show()

        val alertTitle: TextView = dialog.findViewById(R.id.tv_required_filed_msg)
        alertTitle.text = msg
        val okButton: Button = dialog.findViewById(R.id.btn_ok_msg)

        okButton.setOnClickListener {
            if (status) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                dialog.dismiss()

            }
        }

    }

}


