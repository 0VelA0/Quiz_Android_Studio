package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GameSaveDao{


    @Insert
    fun insertsave(preguntasrespondidas: GameSave)


    @Insert
    fun insertsaves(preguntasrespondidas1: GameSave, preguntasrespondidas2: GameSave)

    @Update
    fun updatesave(preguntasRespondidas: Array<GameSave>)


    @Update
    fun updatesaves(vararg preguntasrespondidas: GameSave)

    @Delete
    fun deletesave(preguntasrespondidas: GameSave)


    @Delete
    fun deletesaves(preguntasrespondidas:List<GameSave>)



}