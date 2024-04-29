package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PreguntasDao{


    @Insert
    fun insertpreguntan(pregunta:Preguntas)

    @Insert
    fun insertpreguntasn(pregunta1:Preguntas,pregunta2:Preguntas)


    @Update
    fun updatepreguntan(preguntas: Array<Preguntas>)

    @Update
    fun updateMorepreguntasn(vararg preguntas:Preguntas)

    @Delete
    fun deletepreguntasn(pregunta: Preguntas)

    @Delete
    fun deletepreguntasn(pregunta: List<Preguntas>)

}