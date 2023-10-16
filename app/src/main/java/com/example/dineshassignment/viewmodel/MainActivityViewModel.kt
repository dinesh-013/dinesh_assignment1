package com.example.dineshassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dineshassignment.model.MyData
import com.example.dineshassignment.model.LocalRepo
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class MainActivityViewModel(app : Application) : AndroidViewModel(app) {

    val myData: MutableLiveData<MyData?> = MutableLiveData()
    private var repo : LocalRepo = LocalRepo()

    @OptIn(ExperimentalSerializationApi::class)
    fun retrieveData() {


        viewModelScope.launch {
            val data = repo.retrieveDataFromJsonFile(this@MainActivityViewModel.getApplication())
            myData.postValue(data)
        }
    }
}