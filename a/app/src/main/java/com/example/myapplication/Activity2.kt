package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class Activity2: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
    }

    override fun onResume() {
        super.onResume()
        Log.d("QUIZAPP_DEBUG", "onResume()...")
    }

    override fun onPause() {
        super.onPause()
        Log.d("QUIZAPP_DEBUG", "onPause()...")
    }

    override fun onStop() {
        super.onStop()
        Log.d("QUIZAPP_DEBUG", "onStop()...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("QUIZAPP_DEBUG", "onDestroy()...")
    }
}