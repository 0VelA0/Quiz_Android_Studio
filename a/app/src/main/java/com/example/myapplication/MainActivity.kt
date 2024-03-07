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

    val i = intent
    val dificulty = if (i!=null) i.getStringExtra("DIFICULTAD_EXTRA",) else "Dificil"

    private val SCORE_EXTRA = 50 //cuando ya haya una funcion para calcular el score reemplaza con "SCORE_EXTRA"
    private val HINTACTIVITY_REQUEST_CODE = 0

    private lateinit var preguntatexto: TextView
    private lateinit var puntajefinal: TextView
    private lateinit var Answer1: Button
    private lateinit var Answer2: Button
    private lateinit var Answer3: Button
    private lateinit var Answer4: Button
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

//    private fun revisarRespuesta(userAnswer: Boolean) {
//        val isCorrect = userAnswer == model.respuestaPreguntaActual
//        puntaje += if (isCorrect) 1 else 0
//
//        val message = if (isCorrect) "Respuesta correcta" else "Respuesta incorrecta"
//        mostrarPuntajeTotal()
//        showSnackbar(message)
//    }

    private fun mostrarPuntajeTotal() {
        val totalScoreMessage = "Total Score: $puntaje"
        puntajefinal.text = totalScoreMessage
    }

    private fun randomizeQuestionOrder(){
        var totalRespuestas = arrayListOf<String>()

        when(dificulty){
            "Facil"-> totalRespuestas.add(model.otrasRespuestas.random())
            "Normal" -> totalRespuestas.add(model.otrasRespuestas.random()) && totalRespuestas.add(model.otrasRespuestas.random())
            "Dificil" -> totalRespuestas = model.otrasRespuestas
        }

        if(dificulty == "Normal"){
            if(totalRespuestas[0] == totalRespuestas[1]){
                randomizeQuestionOrder()
            }
        }

        totalRespuestas.add(model.respuestaPreguntaActual)

        totalRespuestas.shuffle()
        Answer1.text = totalRespuestas[0]
        Answer2.text = totalRespuestas[1]
        if(dificulty == "Normal"){
            Answer3.text = totalRespuestas[2]
            Answer3.visibility = View.VISIBLE
        }else if(dificulty == "Dificil"){
            Answer3.text = totalRespuestas[2]
            Answer3.visibility = View.VISIBLE
            Answer4.text = totalRespuestas[3]
            Answer4.visibility = View.VISIBLE
        }
        else{
            Answer3.visibility = View.INVISIBLE
            Answer4.visibility = View.INVISIBLE
        }

    }
    private fun checkAnswer(answer: String){
        if (!model.puntajeInterruptor) {

            if(answer ===  model.respuestaPreguntaActual){
                showSnackbar("Respuesta Correcta")
            }
            else{
                showSnackbar("Respuesta Incorrecta")

            }
            mostrarPuntajeTotal()
            nextButton.visibility = View.VISIBLE
            prevButton.visibility = View.VISIBLE
            model.puntajeInterruptor = true
        } else {
            Toast.makeText(this, "Ya has respondido esta pregunta", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("QUIZAPP_DEBUG", "onCreate()...")
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)



        preguntatexto = findViewById(R.id.pregunta)
        Answer1 = findViewById(R.id.boton_1)
        Answer2 = findViewById(R.id.boton_2)
        Answer3 = findViewById(R.id.boton_3)
        Answer4 = findViewById(R.id.boton_4)
        nextButton = findViewById(R.id.boton_siguiente)
        prevButton = findViewById(R.id.boton_anterior)
        puntajefinal = findViewById(R.id.puntaje_total)
        testButton = findViewById(R.id.test_act)
        preguntatexto.text = model.textoPreguntaActual

        randomizeQuestionOrder()

        Answer1.setOnClickListener {
            checkAnswer(Answer1.text.toString())
        }
        Answer2.setOnClickListener {
            checkAnswer(Answer2.text.toString())
        }

        Answer3.setOnClickListener {
            checkAnswer(Answer3.text.toString())
        }
        Answer4.setOnClickListener {
            checkAnswer(Answer4.text.toString())
        }


        nextButton.setOnClickListener { _ ->
            model.siguientePregunta()
            preguntatexto.text = model.textoPreguntaActual
            nextButton.visibility = View.INVISIBLE
            prevButton.visibility = View.INVISIBLE
            randomizeQuestionOrder()
        }
        prevButton.setOnClickListener { _ ->
            model.anteriorPregunta()
            preguntatexto.text = model.textoPreguntaActual
            prevButton.visibility = View.INVISIBLE
            nextButton.visibility = View.INVISIBLE
            randomizeQuestionOrder()
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

