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
    fun insertpreguntas(preguntas_respondidas1: GameSave, preguntas_respondidas2: GameSave)

    @Update
    fun updatepreguntas(preguntas_Respondidas: Array<GameSave>)


    @Update
    fun updateMorepreguntas(vararg preguntas_respondidas: GameSave)

    @Delete
    fun deletepreguntas(preguntas_respondidas: GameSave)


    @Delete
    fun deletepreguntas(preguntas_respondidas:List<GameSave>)



}