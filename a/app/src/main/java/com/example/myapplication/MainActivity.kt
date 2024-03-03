package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private lateinit var preguntatexto: TextView
    private lateinit var puntajefinal: TextView
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button

    private val model: Preguntas by viewModels()
    private var puntaje = 0

    private fun showSnackbar(message: String){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}