package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface OptionsDificultadDao{

    @Insert
    fun insertdificultad(dificultad: OptionsDificultad)

    @Insert
    fun insertdificultades(dificultad1: OptionsDificultad,dificultad2: OptionsDificultad)


    @Update
    fun updatedificultad(dificultad: Array<OptionsDificultad>)

    @Update
    fun updateMoredificultades(vararg dificultad: OptionsDificultad)

    @Delete
    fun deletedifilcultad(dificultad: OptionsDificultad)

    @Delete
    fun deletedifilcutades(dificultad:List<OptionsDificultad>)

}