package com.example.myapplication

import androidx.lifecycle.ViewModel
class Preguntas : ViewModel() {
    private var preguntaactual = 0

    private val preguntas = listOf<Pregunta>(
        //musica
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Musica"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Musica"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Musica"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Musica"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Musica"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Musica"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Musica"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Musica"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Musica"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Musica"),

        //deportes
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Deporte"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Deporte"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Deporte"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Deporte"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Deporte"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Deporte"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Deporte"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Deporte"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Deporte"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Deporte"),

        //ciencias
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Ciencia"),

        //videojuegos
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Videojuegos"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Videojuegos"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Videojuegos"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Videojuegos"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Videojuegos"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Videojuegos"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Videojuegos"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Videojuegos"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Videojuegos"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Videojuegos"),

        //historia
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Historia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Historia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Historia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Historia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Historia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Historia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Historia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Historia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Historia"),
        Pregunta("¿La luna es de queso?", "", arrayListOf("","",""), "Historia"),
    )

    val textoPreguntaActual: String
        get()= preguntas[preguntaactual].texto

    val respuestaPreguntaActual: String
        get() = preguntas[preguntaactual].respuestaCorrecta

    val otrasRespuestas: ArrayList<String>
        get() = preguntas[preguntaactual].respuestasIncorrectas

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