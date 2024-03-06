package com.example.myapplication

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class Activity1 : AppCompatActivity() {
    private lateinit var image: Image
    private lateinit var jugar: Button
    private lateinit var opciones: Button
    private lateinit var dificultad: Spinner

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        val items = listOf("Facil", "Normal", "Dificil")
        var selectedItem = "Facil"
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        jugar = findViewById(R.id.jugar)
        opciones = findViewById(R.id.Opciones)
        dificultad = findViewById(R.id.spinner_dificultad)

        jugar.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("dificultad", selectedItem)
            startActivity(intent)
        }
        opciones.setOnClickListener{
            val intent = Intent(this,Activity2::class.java)
            startActivity(intent)
        }

        dificultad.adapter = adapter

        dificultad.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItem = items[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedItem = "Facil"
            }
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