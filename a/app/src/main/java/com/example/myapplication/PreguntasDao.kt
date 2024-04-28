package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PreguntasDao{


    @Insert
    fun insertpregunta(pregunta:Preguntas)

    @Insert
    fun insertpreguntas(prugunta1:Preguntas,pregunta2:Preguntas)

    @Insert
    fun insertrespuesta(respuesta:Preguntas)

    @Insert
    fun insertrespuestas(respuesa1:Preguntas,respuesta2:Preguntas)

    @Insert
    fun insertcategoria(categoria: Preguntas)

    @Insert
    fun insertcategorias(categoria1:Preguntas,categoria2:Preguntas)

    @Update
    fun updatepregunta(preguntas: Array<Preguntas>)

    @Update
    fun updaterespuesta(respuestas:Array<Preguntas>)

    @Update
    fun updatecategoria(categorias:Array<Preguntas>)

    @Update
    fun updateMorepreguntas(vararg preguntas:Preguntas)

    @Delete
    fun deletepreguntas(pregunta: Preguntas)

    @Delete
    fun deleteUsers(pregunta: List<Preguntas>)

}