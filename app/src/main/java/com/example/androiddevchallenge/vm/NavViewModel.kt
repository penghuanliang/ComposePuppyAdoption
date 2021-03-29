package com.example.androiddevchallenge.vm

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.Screen

class NavViewModel : ViewModel() {

    private val screen = MutableLiveData<Screen>(Screen.HomeScreen)

    var curScreen: LiveData<Screen> = screen


    @MainThread
    fun onBack(): Boolean {
        if (screen.value != Screen.HomeScreen) {
            screen.value = Screen.HomeScreen
            return true
        }
        return false
    }


    @MainThread
    fun navigateToDetail(puppy: Puppy) {
        screen.value = Screen.DetailScreen(puppy)
    }

}