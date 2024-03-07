package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val HINTACTIVITY_EXTRA_ANSWER = "HINTACTIVITY_EXTRA_ANSWER"

class Pista : AppCompatActivity() {

    private lateinit var answerText: TextView
    private lateinit var hintButton: Button

    private var answer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hint)

        answer = intent.getBooleanExtra(HINTACTIVITY_EXTRA_ANSWER, false)

        answerText = findViewById(R.id.answer_text)
        hintButton = findViewById(R.id.hints_button)

        hintButton.setOnClickListener{ _ ->
            answerText.text = "PISTA UTILIZADA"

            val intent = Intent()
            intent.putExtra(HINTACTIVITY_EXTRA_ANSWER ,if (answer) "VERDADERO" else "FALSO")
            setResult(AppCompatActivity.RESULT_OK, intent)
        }
    }
}