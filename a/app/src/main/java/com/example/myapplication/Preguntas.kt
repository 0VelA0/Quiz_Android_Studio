package com.example.myapplication

import androidx.lifecycle.ViewModel
class Preguntas : ViewModel() {
    private var preguntaactual = 0

    private val preguntas = listOf<Pregunta>(
        //musica
        Pregunta("¿Quién es conocido como \"El Rey del Pop\"?", "Michael Jackson", arrayListOf("Justin Bieber","Elvis Presley","Taylor Swift"), "Musica"),
        Pregunta("¿Cuál de los siguientes géneros musicales se originó en Jamaica?", "Reggae", arrayListOf("Jazz","Flamenco","EDM"), "Musica"),
        Pregunta("¿Quién es la vocalista principal de la banda Paramore?", "Hayley Williams", arrayListOf("Adele","Shakira","Taylor Swift"), "Musica"),
        Pregunta("¿En qué década se lanzó el álbum icónico \"Thriller\" de Michael Jackson?", "1980s", arrayListOf("1990s","2000s","1970s"), "Musica"),
        Pregunta("¿Cuál de las siguientes canciones es de la banda Queen?", "\"Bohemian Rhapsody\"", arrayListOf(" \"Livin' on a Prayer\"","\"Don't Stop Believin'\"","\"Sweet Child o' Mine\""), "Musica"),

        //deportes
        Pregunta("¿Cuál es el deporte que se juega en un campo rectangular con dos equipos de once jugadores cada uno, y el objetivo es marcar goles en la portería del equipo contrario?", "Futbol", arrayListOf("Baloncesto","Golf","Rugby"), "Deportes"),
        Pregunta("¿Qué equipo de fútbol es conocido como \"Los Diablos Rojos\"?", "Manchester United", arrayListOf("FC Barcelona","AC Milan","Bayern Múnich"), "Deportes"),
        Pregunta("¿Cuántos jugadores hay en un equipo de voleibol en la cancha al mismo tiempo?", "6", arrayListOf("5","3","4"), "Deportes"),
        Pregunta("¿En qué deporte se utiliza un bate para golpear una pelota lanzada por un lanzador hacia el campo de juego?", "Béisbol", arrayListOf("Tenis","Cricket","Golf"), "Deportes"),
        Pregunta("¿Cuál es el evento principal en los Juegos Olímpicos de Invierno que combina esquí, tiro y resistencia física?", "Biatlón", arrayListOf("Salto de esquí","Patinaje de velocidad","Curling"), "Deportes"),

        //ciencias
        Pregunta("¿Cuál es la fórmula química del agua?", "H2O", arrayListOf("H2O2","CO2","CH4"), "Ciencia"),
        Pregunta("¿Cuál es el planeta más grande de nuestro sistema solar?", "Jupiter", arrayListOf("Tierra","Venus","Marte"), "Ciencia"),
        Pregunta("¿Cuál es la unidad básica de la vida en los seres vivos?", "Célula ", arrayListOf("Molécula","Átomo","Gen"), "Ciencia"),
        Pregunta("¿Qué gas es esencial para la fotosíntesis de las plantas?", "Dióxido de carbono", arrayListOf("Hidrógeno","Nitrógeno","Oxígeno"), "Ciencia"),
        Pregunta("¿Cuál es la capa más externa de la Tierra?", "Corteza", arrayListOf("Núcleo","Manto","Núcleo interno"), "Ciencia"),

        //videojuegos
        Pregunta("¿Cuál es el nombre del fontanero protagonista en la serie de juegos \"The Legend of Zelda\"?", "Link", arrayListOf("Zelda","Ganondorf","Mario"), "Videojuegos"),
        Pregunta("¿En qué juego de rol puedes encontrar personajes como Aerith, Cloud y Sephiroth?", "Final Fantasy VII", arrayListOf("Kingdom Hearts","Dragon Quest","Persona 5"), "Videojuegos"),
        Pregunta("¿Cuál de los siguientes juegos es conocido por su modo battle royale en el que 100 jugadores compiten hasta que solo queda uno en pie?", "Fortnite", arrayListOf("Apex Legends","Call of Duty","Overwatch"), "Videojuegos"),
        Pregunta("¿Cuál de los siguientes NO es un juego de Shin Megami Tensei?", "Shadows of Almia", arrayListOf("Persona 3","Devil Survivor","Strange Journey"), "Videojuegos"),
        Pregunta("¿Cuál es el nombre del protagonista en la saga de juegos \"The Witcher\"?", "Geralt of Rivia", arrayListOf("Ezio Auditore","Joel Miller","Arthas Menethil"), "Videojuegos"),

        //historia
        Pregunta("¿En qué año comenzó la Primera Guerra Mundial?", "1914", arrayListOf("1920","1945","1935"), "Historia"),
        Pregunta("¿Cuál de estos eventos históricos ocurrió primero?", "La Revolución Francesa", arrayListOf("La Revolución Rusa","La Revolución Americana","La Revolución Industrial"), "Historia"),
        Pregunta("¿Qué país fue gobernado por el zar Nicolás II hasta la Revolución Rusa de 1917?", "Rusia", arrayListOf("Alemania","Francia","Italia"), "Historia"),
        Pregunta("¿Cuál de los siguientes eventos históricos involucró la famosa \"Carrera Espacial\"", "Guerra Fría", arrayListOf("Revolución Industrial","Renacimiento","Edad Media"), "Historia"),
        Pregunta("¿Qué líder militar y político francés es conocido por ser de baja estatura y por su complejo de inferioridad?", "Napoleón Bonaparte", arrayListOf("Charles de Gaulle","Luis XIV","Robespierre"), "Historia")
    )

    private val preguntasReales = preguntas.shuffled().subList(0,10)
    val textoPreguntaActual: String
        get()= preguntasReales[preguntaactual].texto

    val respuestaPreguntaActual: String
        get() = preguntasReales[preguntaactual].respuestaCorrecta

    val categoria: String
        get() = preguntasReales[preguntaactual].Categoria

    val otrasRespuestas: ArrayList<String>
        get() = preguntasReales[preguntaactual].respuestasIncorrectas

    var puntajeInterruptor: Boolean
        get() = preguntasReales[preguntaactual].resactivador ?: false
        set(value) {
            preguntasReales[preguntaactual].resactivador = value
        }

    var respondida: Boolean
        get() = preguntasReales[preguntaactual].respondedCorrectly ?: false
        set(value) {
            preguntasReales[preguntaactual].respondedCorrectly = value
        }

    val totalPreguntas: Int
        get() = preguntasReales.size

    fun siguientePregunta(){
        preguntaactual = (preguntaactual + 1) % preguntasReales.size
    }

    fun  anteriorPregunta(){
        preguntaactual = (preguntaactual - 1 + preguntasReales.size) % preguntasReales.size
    }
}