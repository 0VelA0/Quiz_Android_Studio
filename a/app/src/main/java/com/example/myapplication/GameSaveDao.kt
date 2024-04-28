package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameSaveDao{


    @Insert
    fun insertpregunta(preguntasrespondidas: GameSave)


    @Insert
    fun insertpreguntas(preguntasrespondidas1: GameSave, preguntasrespondidas2: GameSave)

    @Update
    fun updatepreguntas(preguntasRespondidas: Array<GameSave>)


    @Update
    fun updateMorepreguntas(vararg preguntasrespondidas: GameSave)

    @Delete
    fun deletepreguntas(preguntasrespondidas: GameSave)


    @Delete
    fun deletepreguntas(preguntasrespondidas:List<GameSave>)



}