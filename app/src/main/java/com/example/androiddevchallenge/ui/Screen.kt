package com.example.androiddevchallenge.ui

import com.example.androiddevchallenge.data.Puppy

sealed class Screen {

    object HomeScreen : Screen()

    data class DetailScreen(val puppy: Puppy) : Screen()
}
