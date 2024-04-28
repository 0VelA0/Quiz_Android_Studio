package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Index
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameSaveDao{


    @Insert
    fun insertpregunta(preguntas_respondidas: GameSave)

    @Insert
    fun insertrespuesta(respuestas_usadas: GameSave)

    @Insert
    fun insertpreguntas(preguntas_respondidas1: GameSave, preguntas_respondidas2: GameSave)

    @Insert
    fun insertrespuestas(respuestas_usadas1: GameSave, respuestas_usadas2: GameSave)

    @Update
    fun updatepreguntas(preguntas_Respondidas: Array<GameSave>)

    @Update
    fun updaterespuestas(respuestas_usadas:Array<GameSave>)

    @Update
    fun updateMorepreguntas(vararg preguntas_respondidas: GameSave)

    @Update
    fun updaterespuestas(vararg respuestas_usadas: GameSave)

    @Delete
    fun deletepreguntas(preguntas_respondidas: GameSave)

    @Delete
    fun deleterespuestas(respuestas_usadas: GameSave)

    @Delete
    fun deletepreguntas(preguntas_respondidas:List<GameSave>)

    @Delete
    fun deleterespuestas(respuestas_usadas: List<GameSave>)

}