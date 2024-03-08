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
import org.w3c.dom.Text
import kotlin.math.log

class MainActivity : AppCompatActivity() {


    private var dificulty = if (intent!=null) intent.getStringExtra("DIFICULTAD_EXTRA") else "Normal"

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
    private lateinit var hint_text: TextView
    private lateinit var streak_text: TextView

    private val model: Preguntas by viewModels()
    private var puntaje: Int = 0
    private var answered: Int = 0
    private var total: Int = 0
    private var streak: Int = 0
    private var bonusGive: Boolean = true
    private var randomized: Boolean = false
    private var CurrentAnswers = arrayListOf<String>()

        private fun showSnackbar(message: String) {
        val coordinatorLayout = findViewById<CoordinatorLayout>(R.id.coordinator)
        val snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("OK", View.OnClickListener {})
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
            intent.putExtra("DIFFICULTYMOD", if(dificulty == "Facil") 1 else if(dificulty == "Normal") 2 else 3) // ESTA PARTE MANDA INFO ENTRE ACTIVITIES
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

    private fun mostrarHints() {
        val hintText = "Pistas restantes: $remainingHints"
        hint_text.text = hintText
    }

    private fun mostrarStreak() {
        val streakText = "Racha: $streak"
        streak_text.text = streakText
    }

    private fun randomizeQuestionOrder(){
        bonusGive = true
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

        if(CurrentAnswers.size == 0){
            totalRespuestas.add(model.respuestaPreguntaActual)
            totalRespuestas.shuffle()
            CurrentAnswers = totalRespuestas
        }
        else{
            totalRespuestas = CurrentAnswers
        }

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

        if(model.puntajeInterruptor){
            hintButton.visibility = View.INVISIBLE
            nextButton.visibility = View.VISIBLE
            prevButton.visibility = View.VISIBLE
            if(model.respondida){
                preguntatexto.setTextColor(Color.GREEN)
            }
            else{
                preguntatexto.setTextColor(Color.RED)
            }

        }
        else{
            preguntatexto.setTextColor(Color.BLACK)
            hintButton.visibility = View.VISIBLE
            nextButton.visibility = View.INVISIBLE
            prevButton.visibility = View.INVISIBLE
        }

        randomized = true
    }

    private fun useHint(){
        if(remainingHints > 0 && bonusGive){
            bonusGive = false
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
                if(Answer1.text != model.respuestaPreguntaActual){
                    Answer1.setBackgroundColor(Color.RED)
                }
                else if(Answer2.text != model.respuestaPreguntaActual){
                    Answer2.setBackgroundColor(Color.RED)
                }
                else if(Answer3.text != model.respuestaPreguntaActual){
                    Answer3.setBackgroundColor(Color.RED)
                }
                else if(Answer4.text != model.respuestaPreguntaActual){
                    Answer4.setBackgroundColor(Color.RED)
                }
            }
            remainingHints-=1
            mostrarHints()
        }
        else if(bonusGive){
            Toast.makeText(this, "No te quedan hints", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Ya usaste una hint para esta pregunta", Toast.LENGTH_SHORT).show()
        }
    }
    private fun checkAnswer(b: Button){
        if (!model.puntajeInterruptor) {
            hintButton.visibility = View.INVISIBLE
            if(b.text.toString() ===  model.respuestaPreguntaActual){
                showSnackbar("Respuesta Correcta")
                var difmod = 0
                when(dificulty){
                    "Facil"-> difmod = 1
                    "Normal"-> difmod = 2
                    "Dificil"-> difmod = 3
                }
                puntaje += if (!bonusGive) (1*difmod) else (2*difmod)
                b.setBackgroundColor(Color.GREEN)
                model.respondida = true
                if(bonusGive){
                    streak+=1
                    mostrarStreak()
                    if(streak>=2){
                        remainingHints+=1
                        mostrarHints()
                    }
                }
            }
            else{
                showSnackbar("Respuesta Incorrecta")
                b.setBackgroundColor(Color.RED)
                streak = 0
                mostrarStreak()
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
        dificulty = intent.getStringExtra("DIFICULTAD_EXTRA")
        Log.d("QUIZAPP_DEBUG", "onCreate: savedInstanceState is ${if (savedInstanceState != null) "not" else ""} null")

        if(savedInstanceState != null){
            randomized = savedInstanceState.getBoolean("randomized")
            CurrentAnswers = savedInstanceState.getStringArrayList("answers")!!
        }
        else{randomized = false}
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
        hint_text = findViewById(R.id.hints)
        streak_text = findViewById(R.id.streak)

        randomizeQuestionOrder()

        Answer1.setOnClickListener {
            checkAnswer(Answer1)
        }
        Answer2.setOnClickListener {
            checkAnswer(Answer2)
        }

        Answer3.setOnClickListener {
            checkAnswer(Answer3)
        }
        Answer4.setOnClickListener {
            checkAnswer(Answer4)
        }

        hintButton.setOnClickListener { _ ->
            useHint()
        }

        nextButton.setOnClickListener { _ ->
            model.siguientePregunta()
            preguntatexto.text = model.textoPreguntaActual
            nextButton.visibility = View.INVISIBLE
            prevButton.visibility = View.INVISIBLE
            randomized = false
            CurrentAnswers.clear()
            randomizeQuestionOrder()
        }
        prevButton.setOnClickListener { _ ->
            model.anteriorPregunta()
            preguntatexto.text = model.textoPreguntaActual
            prevButton.visibility = View.INVISIBLE
            nextButton.visibility = View.INVISIBLE
            randomized = false
            CurrentAnswers.clear()
            randomizeQuestionOrder()
        }

   }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("QUIZAPP_DEBUG", "onSaveInstanceState: saving state")
        outState.putInt("REMAINING_HINTS", remainingHints)
        outState.putString("DIFICULTY", dificulty)
        outState.putInt("PUNTAJE", puntaje)
        outState.putInt("ANSWERED", answered)
        outState.putInt("TOTAL", total)
        outState.putInt("STREAK", streak)
        outState.putBoolean("randomized", randomized)
        outState.putStringArrayList("answers", CurrentAnswers)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        remainingHints = savedInstanceState.getInt("REMAINING_HINTS")
        dificulty = savedInstanceState.getString("DIFICULTY", "Normal")
        puntaje = savedInstanceState.getInt("PUNTAJE")
        answered = savedInstanceState.getInt("ANSWERED")
        total = savedInstanceState.getInt("TOTAL")
        streak = savedInstanceState.getInt("STREAK")
        randomized = savedInstanceState.getBoolean("randomized")
        CurrentAnswers = savedInstanceState.getStringArrayList("answers")!!

        mostrarHints()
        mostrarPuntajeTotal()
        mostrarStreak()
    }
}

