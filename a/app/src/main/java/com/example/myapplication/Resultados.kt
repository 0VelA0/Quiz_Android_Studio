package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Resultados : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var scorePoints: TextView
    private lateinit var imgScore: ImageView
    private lateinit var scoreMessage: TextView
    private lateinit var startAgainButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        resultText = findViewById(R.id.result_text)
        scorePoints = findViewById(R.id.score_points)
        imgScore = findViewById(R.id.img_score)
        scoreMessage = findViewById(R.id.score_message)
        startAgainButton = findViewById(R.id.start_again_button)

        val score = intent.getIntExtra("SCORE_EXTRA", 0)

        when (score) {
            in 0 until 50 -> {
                imgScore.setImageResource(R.drawable.really_bad)
                scoreMessage.text = "Te fue muy mal wey"
            }

            in 50 until 70 -> {
                imgScore.setImageResource(R.drawable.bad)
                scoreMessage.text = "Puedes Mejorar"
            }

            in 70 until 90 -> {
                imgScore.setImageResource(R.drawable.good)
                scoreMessage.text = "Muy Bien!"
            }

            in 90..100 -> {
                imgScore.setImageResource(R.drawable.really_good)
                scoreMessage.text = "Excelente!"
            }
        }

        scorePoints.text = score.toString()

        startAgainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}


