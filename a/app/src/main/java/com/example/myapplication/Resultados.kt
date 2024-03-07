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
    private var score: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        resultText = findViewById(R.id.result_text)
        scorePoints = findViewById(R.id.score_points)
        imgScore = findViewById(R.id.img_score)
        scoreMessage = findViewById(R.id.score_message)
        startAgainButton = findViewById(R.id.start_again_button)

        score = intent.getIntExtra("SCORE_EXTRA", 0)

        if (savedInstanceState != null){
            score = savedInstanceState.getInt("CURRENT_SCORE", 0)
        }

        when (score) {
            in 0 until 10 -> {
                imgScore.setImageResource(R.drawable.really_bad)
                scoreMessage.text = getString(R.string.really_bad_score_text)
            }

            in 10 until 30 -> {
                imgScore.setImageResource(R.drawable.bad)
                scoreMessage.text = getString(R.string.bad_score_text)
            }

            in 30 until 50 -> {
                imgScore.setImageResource(R.drawable.good)
                scoreMessage.text = getString(R.string.good_score_string)
            }

            in 50..60 -> {
                imgScore.setImageResource(R.drawable.really_good)
                scoreMessage.text = getString(R.string.great_score_string)
            }
        }

        scorePoints.text = score.toString()

        startAgainButton.setOnClickListener {
            val intent = Intent(this, Activity1::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("CURRENT_SCORE", score)
        super.onSaveInstanceState(outState)
    }
}




