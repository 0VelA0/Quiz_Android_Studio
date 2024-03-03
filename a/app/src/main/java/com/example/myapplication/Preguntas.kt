package com.example.myapplication

import androidx.lifecycle.ViewModel
class Preguntas : ViewModel() {
    private var preguntaactual = 0

    private val preguntas = listOf<Pregunta>(
        Pregunta("¿La luna es de queso?", true),
        Pregunta("¿La luna es de hueso?", false),
        Pregunta("¿2 + 2 = 4?", true),
        Pregunta("¿Si o no?", true),
        Pregunta("¿Los hot dogs son reales?", true),
        Pregunta("¿Te bañaste hoy?", false)
    )

    val textoPreguntaActual: String
        get()= preguntas[preguntaactual].texto

    val respuestaPreguntaActual: Boolean
        get() = preguntas[preguntaactual].respuesta

    var puntajeInterruptor: Boolean
        get() = preguntas[preguntaactual].resactivador ?: false
        set(value) {
            preguntas[preguntaactual].resactivador = value
        }

    fun siguientePregunta(){
        preguntaactual = (preguntaactual + 1) % preguntas.size
    }

    fun  anteriorPregunta(){
        preguntaactual = (preguntaactual - 1 + preguntas.size) % preguntas.size
    }
}