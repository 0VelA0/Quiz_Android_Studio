package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.coordinatorlayout.widget.CoordinatorLayout

class MainActivity : AppCompatActivity() {

    private val SCORE_EXTRA = 50 //cuando ya haya una funcion para calcular el score reemplaza con "SCORE_EXTRA"
    private val HINTACTIVITY_REQUEST_CODE = 0

    private lateinit var preguntatexto: TextView
    private lateinit var puntajefinal: TextView
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var testButton: Button

    private val model: Preguntas by viewModels()
    private var puntaje = 0

    private fun showSnackbar(message: String) {
        val coordinatorLayout = findViewById<CoordinatorLayout>(R.id.coordinator)
        val snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Death", View.OnClickListener {})
        snackbar.show()
    }

    private fun revisarRespuesta(userAnswer: Boolean) {
        val isCorrect = userAnswer == model.respuestaPreguntaActual
        puntaje += if (isCorrect) 1 else 0

        val message = if (isCorrect) "Respuesta correcta" else "Respuesta incorrecta"
        mostrarPuntajeTotal()
        showSnackbar(message)
    }

    private fun mostrarPuntajeTotal() {
        val totalScoreMessage = "Total Score: $puntaje"
        puntajefinal.text = totalScoreMessage
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("QUIZAPP_DEBUG", "onCreate()...")
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)


        preguntatexto = findViewById(R.id.pregunta)
        yesButton = findViewById(R.id.boton_si)
        noButton = findViewById(R.id.boton_no)
        nextButton = findViewById(R.id.boton_siguiente)
        prevButton = findViewById(R.id.boton_anterior)
        puntajefinal = findViewById(R.id.puntaje_total)
        testButton = findViewById(R.id.test_act)
        preguntatexto.text = model.textoPreguntaActual

        yesButton.setOnClickListener { _ ->
            if (!model.puntajeInterruptor) {
                revisarRespuesta(true)
                model.puntajeInterruptor = true
            } else {
                Toast.makeText(this, "Ya has respondido esta pregunta", Toast.LENGTH_SHORT).show()
            }
            nextButton.visibility = View.VISIBLE
            prevButton.visibility = View.VISIBLE
        }

        noButton.setOnClickListener { _ ->
            if (!model.puntajeInterruptor) {
                revisarRespuesta(false)
                model.puntajeInterruptor = true
            } else {
                Toast.makeText(this, "Ya has respondido esta pregunta", Toast.LENGTH_SHORT).show()
            }
            nextButton.visibility = View.VISIBLE
            prevButton.visibility = View.VISIBLE
        }


        nextButton.setOnClickListener { _ ->
            model.siguientePregunta()
            preguntatexto.text = model.textoPreguntaActual
            nextButton.visibility = View.INVISIBLE
            prevButton.visibility = View.INVISIBLE
        }
        prevButton.setOnClickListener { _ ->
            model.anteriorPregunta()
            preguntatexto.text = model.textoPreguntaActual
            prevButton.visibility = View.INVISIBLE
            nextButton.visibility = View.INVISIBLE
        }


        preguntatexto.setOnClickListener { _ ->
            val intent = Intent(this, Pista::class.java)
            intent.putExtra(HINTACTIVITY_EXTRA_ANSWER, model.respuestaPreguntaActual)
            startActivityForResult(intent, HINTACTIVITY_REQUEST_CODE)
        }



        // Este codigo es un test para cambiar entre activities pueden borrarlo si no lo necesitan
        testButton.setOnClickListener {
            val intent = Intent(this, Resultados::class.java)
            //intent.putExtra("SCORE_EXTRA", funcioncalcularscore()) //que esta funcion que crees retorne un valor
            intent.putExtra("SCORE_EXTRA", 100) // ESTA PARTE MANDA INFO ENTRE ACTIVITIES
            startActivity(intent)
        }
   }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
}

