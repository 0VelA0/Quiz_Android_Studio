package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.coordinatorlayout.widget.CoordinatorLayout

class MainActivity : AppCompatActivity() {

    val i = intent
    val dificulty = if (i!=null) i.getStringExtra("DIFICULTAD_EXTRA") else "Normal"

    private val HINTACTIVITY_REQUEST_CODE = 0

    private var remainingHints = 5

    private lateinit var preguntatexto: TextView
    private lateinit var puntajefinal: TextView
    private lateinit var Answer1: Button
    private lateinit var Answer2: Button
    private lateinit var Answer3: Button
    private lateinit var Answer4: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var hintButton: Button
    private lateinit var ImageView: Image

    private val model: Preguntas by viewModels()
    private var puntaje = 0
    private var answered = 0
    private var total = 0

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


    private fun compareAnswered(){
        if (answered == total){
            val intent = Intent(this, Resultados::class.java)
            intent.putExtra("SCORE_EXTRA", puntaje) // ESTA PARTE MANDA INFO ENTRE ACTIVITIES
            startActivity(intent)
        }
        else{
            return
        }
    }
    private fun mostrarPuntajeTotal() {
        val totalScoreMessage = "Total Score: $puntaje"
        puntajefinal.text = totalScoreMessage
    }

    private fun randomizeQuestionOrder(){
        configureImageByCategory(model.categoria)
        var totalRespuestas = arrayListOf<String>()

        when(dificulty){
            "Facil"-> totalRespuestas.add(model.otrasRespuestas.random())
            "Normal" -> totalRespuestas.add(model.otrasRespuestas.random()) && totalRespuestas.add(model.otrasRespuestas.random())
            "Dificil" -> totalRespuestas = model.otrasRespuestas
        }

        if(dificulty == "Normal"){
            while(totalRespuestas[0] == totalRespuestas[1]){
                totalRespuestas.clear()
                totalRespuestas.add(model.otrasRespuestas.random()) && totalRespuestas.add(model.otrasRespuestas.random())
            }
        }

        totalRespuestas.add(model.respuestaPreguntaActual)

        totalRespuestas.shuffle()
        Answer1.text = totalRespuestas[0]
        Answer2.text = totalRespuestas[1]
        Answer1.setBackgroundColor(Color.BLACK)
        Answer2.setBackgroundColor(Color.BLACK)
        if(dificulty == "Normal"){
            Answer3.text = totalRespuestas[2]
            Answer3.setBackgroundColor(Color.BLACK)
            Answer4.visibility = View.INVISIBLE

        }else if(dificulty == "Dificil"){
            Answer3.text = totalRespuestas[2]
            Answer3.setBackgroundColor(Color.BLACK)
            Answer4.text = totalRespuestas[3]
            Answer4.setBackgroundColor(Color.BLACK)
        }
        else{
            Answer3.visibility = View.INVISIBLE
            Answer4.visibility = View.INVISIBLE
        }

    }

    private fun useHint(){
        if(remainingHints > 0){
            if(dificulty == "Facil"){
                if(Answer1.text == model.respuestaPreguntaActual){
                    Answer1.setBackgroundColor(Color.GREEN)
                }
                else if(Answer2.text == model.respuestaPreguntaActual){
                    Answer2.setBackgroundColor(Color.GREEN)
                }
            }
            else if(dificulty == "Normal"){
                if(Answer1.text != model.respuestaPreguntaActual){
                    Answer1.setBackgroundColor(Color.RED)
                }
                else if(Answer2.text != model.respuestaPreguntaActual){
                    Answer2.setBackgroundColor(Color.RED)
                }
                else if(Answer3.text != model.respuestaPreguntaActual){
                    Answer3.setBackgroundColor(Color.RED)
                }
            }
            else if(dificulty == "Dificil"){
                if(Answer1.text == model.respuestaPreguntaActual){
                    Answer1.setBackgroundColor(Color.GREEN)
                }
                else if(Answer2.text == model.respuestaPreguntaActual){
                    Answer2.setBackgroundColor(Color.GREEN)
                }
                else if(Answer3.text != model.respuestaPreguntaActual){
                    Answer3.setBackgroundColor(Color.RED)
                }
                else if(Answer4.text != model.respuestaPreguntaActual){
                    Answer4.setBackgroundColor(Color.RED)
                }
            }
        }
        else{
            Toast.makeText(this, "No te quedan hints", Toast.LENGTH_SHORT).show()
        }
    }
    private fun checkAnswer(answer: String){
        if (!model.puntajeInterruptor) {

            if(answer ===  model.respuestaPreguntaActual){
                showSnackbar("Respuesta Correcta")
                puntaje += 2
            }
            else{
                showSnackbar("Respuesta Incorrecta")
            }
            answered+=1
            model.puntajeInterruptor = true
        } else {
            Toast.makeText(this, "Ya has respondido esta pregunta", Toast.LENGTH_SHORT).show()
        }
        mostrarPuntajeTotal()
        compareAnswered()
        nextButton.visibility = View.VISIBLE
        prevButton.visibility = View.VISIBLE
    }

    private fun configureImageByCategory(categoria: String) {
        val imageCategoria = findViewById<ImageView>(R.id.imageCategoria)

        when (categoria) {
            "Musica" -> imageCategoria.setImageResource(R.drawable.imagen_musica)
            "Deportes" -> imageCategoria.setImageResource(R.drawable.imagen_deportes)
            "Ciencia" -> imageCategoria.setImageResource(R.drawable.imagen_ciencia)
            "Videojuegos" -> imageCategoria.setImageResource(R.drawable.imagen_videojuegos)
            "Historia" -> imageCategoria.setImageResource(R.drawable.imagen_historia)

            else -> imageCategoria.setImageResource(R.drawable.default_image)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("QUIZAPP_DEBUG", "onCreate()...")
        super.onCreate(savedInstanceState)

        total = model.totalPreguntas

        setContentView(R.layout.activity_main)

        preguntatexto = findViewById(R.id.pregunta)
        Answer1 = findViewById(R.id.boton_1)
        Answer2 = findViewById(R.id.boton_2)
        Answer3 = findViewById(R.id.boton_3)
        Answer4 = findViewById(R.id.boton_4)
        nextButton = findViewById(R.id.boton_siguiente)
        prevButton = findViewById(R.id.boton_anterior)
        puntajefinal = findViewById(R.id.puntaje_total)
        hintButton = findViewById(R.id.test_act)
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

        hintButton.setOnClickListener { _ ->
            useHint()
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

