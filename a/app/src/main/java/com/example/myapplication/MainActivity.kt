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
        val coordinatorLayout = findViewById<CoordinatorLayout>(R.id.coordinator)
        val snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Death", View.OnclickListener {})
        snackbar.show()
    }

    private fun revisarRespuesta(userAnswer: Boolean) {
        val isCorrect = userAnswer == model.respuestaPreguntaActual
        score += if (isCorrect) 1 else 0

        val message = if (isCorrect) "Respuesta correcta" else "Respuesta incorrecta"
        mostrarPuntajeTotal()
        showSnackbar(message)
    }

    private fun mostrarPuntajeTotal() {
        val totalScoreMessage = "Total Score: $score"
        finalScore.text = totalScoreMessage
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("QUIZAPP_DEBUG", "onCreate()...")
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)


        questionText = findViewById(R.id.pregunta)
        yesButton = findViewById(R.id.boton_si)
        noButton = findViewById(R.id.boton_no)
        nextButton = findViewById(R.id.boton_siguiente)
        prevButton = findViewById(R.id.boton_anterior)
        finalScore = findViewById(R.id.puntaje_total)

        textoPregunta.text= model.textoPreguntaActual

        yesButton.setOnClickListener { _ ->
            if (!model.puntajeInterruptor) {
                checkAnswer(true)
                model.puntajeInterruptor = true
            } else {
                Toast.makeText(this, "Ya has respondido esta pregunta", Toast.LENGTH_SHORT).show()
            }
            nextButton.visibility = View.VISIBLE
            prevButton.visibility = View.VISIBLE
        }

        noButton.setOnClickListener { _ ->
            if (!model.puntajeInterruptor) {
                checkAnswer(false)
                model.puntajeInterruptor = true
            } else {
                Toast.makeText(this, "Ya has respondido esta pregunta", Toast.LENGTH_SHORT).show()
            }
            nextButton.visibility = View.VISIBLE
            prevButton.visibility = View.VISIBLE
        }


        nextButton.setOnClickListener { _ ->
            model.siguientePregunta()
            questionText.text = model.puntajeInterruptor
            nextButton.visibility = View.INVISIBLE
            prevButton.visibility = View.INVISIBLE
        }
        prevButton.setOnClickListener { _ ->
            model.anteriorPregunta()
            questionText.text = model.textoPreguntaActual
            prevButton.visibility = View.INVISIBLE
            nextButton.visibility = View.INVISIBLE
        }


        questionText.setOnClickListener { _ ->
            val intent = Intent(this, HintActivity::class.java)
            intent.putExtra(HINTACTIVITY_EXTRA_ANSWER, model.respuestaPreguntaActual)
            startActivityForResult(intent, HINTACTIVITY_REQUEST_CODE)
        }
    }

    override fun oAnctivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            HINTACTIVITY_REQUEST_CODE ->
                when (resultCode) {
                    RESULT_OK ->
                        Toast.makeText(
                            this,
                            "¡HINT: ${data!!.getStringExtra(HINTACTIVITY_EXTRA_ANSWER)}!",
                            Toast.LENGTH_SHORT
                        ).show()

                    RESULT_CANCELED -> Toast.makeText(
                        this,
                        "¡ACTIVITY CERRADO!",
                        Toast.LENGTH_SHORT
                    ).show()
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