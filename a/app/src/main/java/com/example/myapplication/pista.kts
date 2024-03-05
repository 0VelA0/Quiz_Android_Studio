package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val HINTACTIVITY_EXTRA_ANSWER = "HINTACTIVITY_EXTRA_ANSWER"

class HintActivity : AppCompatActivity() {

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
            answerText.text = if (answer) "VERDADERO" else "FALSO"

            val intent = Intent()
            intent.putExtra(HINTACTIVITY_EXTRA_ANSWER ,if (answer) "VERDADERO" else "FALSO")
            setResult(RESULT_OK, intent)
        }
    }
}