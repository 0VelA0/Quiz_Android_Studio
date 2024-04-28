package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class Activity2: AppCompatActivity() {

    private lateinit var btn_Setting: Button
    private lateinit var dificultad: Spinner
    private var selectedItem: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val items = listOf("Facil", "Normal", "Dificil")
        var selectedItem = "Facil"
        btn_Setting = findViewById(R.id.Guardar)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        dificultad = findViewById(R.id.spinner_dificultad)

        if (savedInstanceState != null) {
            selectedItem = savedInstanceState.getString("CURRENT_diff", "Fácil")
        }


        //Log.d("debug selected item", selectedItem) esto falta estaba deentro de un intent ahora se tendra que almacenar en la base de datos

        dificultad.adapter = adapter

        dificultad.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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

        btn_Setting.setOnClickListener {
            val intent = Intent(this, Activity1::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
        }



    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("CURRENT_diff", selectedItem)
        super.onSaveInstanceState(outState)
    }
}
