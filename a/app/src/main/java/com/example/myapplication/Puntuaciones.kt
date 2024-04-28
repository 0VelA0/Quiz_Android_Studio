package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class Puntuaciones : AppCompatActivity() {

    private lateinit var top20: ListView
    private lateinit var Iniciobtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntuaciones)

        top20 = findViewById(R.id.puntuaciones_Score)
        Iniciobtn = findViewById(R.id.Back_to_tile_btn)

        val topScoresList = listOf(
            "Score 1",
            "Score 2",
            "Score 3",
            "Score 4",
            "Score 5",
            "Score 6",
            "Score 7",
            "Score 8",
            "Score 9",
            "Score 10",
            "Score 11",
            "Score 12",
            "Score 13",
            "Score 14",
            "Score 15",
            "Score 16",
            "Score 17",
            "Score 18",
            "Score 19",
            "Score 20"
        )

        val limitedList = if (topScoresList.size > 20) topScoresList.subList(0, 20) else topScoresList

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, limitedList)

        top20.adapter = adapter



        Iniciobtn.setOnClickListener {
            val intent = Intent(this, Activity1::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
        }
    }
}