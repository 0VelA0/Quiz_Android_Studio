package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface RespuestaFalsaDao{

    @Insert
    fun insertrespuesta(respuestaFalsa: RespuestaFalsa)

    @Insert
    fun insertrespuestas(respuestaFalsa1: RespuestaFalsa, respuestaFalsa2: RespuestaFalsa)


    @Update
    fun updaterespuesta(respuestaFalsa: Array<RespuestaFalsa>)

    @Update
    fun updateMorerespuestas(vararg respuestaFalsa: RespuestaFalsa)

    @Delete
    fun deleterespuesta(respuestaFalsa: RespuestaFalsa)

    @Delete
    fun deleterespuestas(respuestaFalsa:List<RespuestaFalsa>)

}