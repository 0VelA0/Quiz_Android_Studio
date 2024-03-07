package com.example.myapplication

import androidx.lifecycle.ViewModel
class Preguntas : ViewModel() {
    private var preguntaactual = 0

    private val preguntas = listOf<Pregunta>(
        //musica
        Pregunta("¿Quién es conocido como \"El Rey del Pop\"?", "Michael Jackson", arrayListOf("Justin Bieber","Elvis Presley","Taylor Swift"), "Musica"),
        Pregunta("¿Cuál de los siguientes géneros musicales se originó en Jamaica?", "Reggae", arrayListOf("Jazz","Flamenco","EDM"), "Musica"),

        //deportes
        Pregunta("¿Cuál es el deporte que se juega en un campo rectangular con dos equipos de once jugadores cada uno, y el objetivo es marcar goles en la portería del equipo contrario", "Futbol", arrayListOf("Baloncesto","Golf","Rugby"), "Deportes"),
        Pregunta("¿Cuántos jugadores hay en un equipo de voleibol en la cancha al mismo tiempo?", "6", arrayListOf("5","3","4"), "Deportes"),

        //ciencias
        Pregunta("¿Cuál es la fórmula química del agua?", "H2O", arrayListOf("H2O2","CO2","CH4"), "Ciencia"),
        Pregunta(" ¿Cuál es el planeta más grande de nuestro sistema solar?", "Jupiter", arrayListOf("Tierra","Venus","Marte"), "Ciencia"),

        //videojuegos
        Pregunta("¿Cuál es el nombre del fontanero protagonista en la serie de juegos \"The Legend of Zelda\"?", "Link", arrayListOf("Zelda","Ganondorf","Mario"), "Videojuegos"),
        Pregunta("¿En qué juego de rol puedes encontrar personajes como Aerith, Cloud y Sephiroth?", "Final Fantasy VII", arrayListOf("Kingdom Hearts","Dragon Quest","Persona 5"), "Videojuegos"),

        //historia
        Pregunta("¿En qué año comenzó la Primera Guerra Mundial?", "1914", arrayListOf("1920","1945","1935"), "Historia"),
        Pregunta("¿Cuál de estos eventos históricos ocurrió primero?", "La Revolución Francesa", arrayListOf("La Revolución Rusa","La Revolución Americana","La Revolución Industrial"), "Historia"),
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