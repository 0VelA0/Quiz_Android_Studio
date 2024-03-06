package com.example.myapplication

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Activity1 : AppCompatActivity() {
    private lateinit var image: Image
    private lateinit var jugar: Button
    private lateinit var opciones: Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        jugar = findViewById(R.id.jugar)
        opciones = findViewById(R.id.Opciones)

        jugar.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        opciones.setOnClickListener{
            val intent = Intent(this,Activity2::class.java)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d("QUIZAPP_DEBUG", "onStart()...")
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