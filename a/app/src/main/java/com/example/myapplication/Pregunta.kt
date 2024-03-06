package com.example.myapplication

data class Pregunta(val texto: String, val respuestaCorrecta: String, val respuestasIncorrectas: ArrayList<String>, val Categoria: String,var resactivador: Boolean=false)