package com.example.demoapp.viewmodlelivedataflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactory(private val repository: UserInformationRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if(modelClass.isAssignableFrom(SharedVM::class.java)){
                SharedVM(this.repository) as T
            }else{
                throw IllegalArgumentException("View Model Not Found")
            }
    }
}