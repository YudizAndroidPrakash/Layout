package com.example.demoapp.viewmodlelivedataflow

import android.util.Log

class Car(private val engine: Engine,private val wheel : Wheel) {

    fun car() {
        engine.engine()
        wheel.wheel()
        Log.e("car is started","Lets Go")
    }

}

class Engine(){

    fun engine(){
        Log.e("engine","Engine")
    }

}

class Wheel(){
    fun wheel(){
        Log.e("wheel","Lets Run")
    }

}