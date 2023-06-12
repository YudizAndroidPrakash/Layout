package com.example.demoapp.koindemo

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil(context: Context){
    val add = context.getSharedPreferences("koin_data",Context.MODE_PRIVATE)
    val  editor = add.edit()
    fun setNumber(number : Int){
        editor.putInt("number",number)
    }

    fun setName(name : String){
        editor.putString("name",name)
    }

    fun getNumber() : Int{
        return add.getInt("number",0)
    }
    fun getName() : String{
         val  name = add.getString("name","")
        return  name!!
    }
    fun saveData(){
        editor.commit()
    }













}