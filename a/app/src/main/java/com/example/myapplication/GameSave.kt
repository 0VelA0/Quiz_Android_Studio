package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "game_save", indices = [Index(value = ["preguntas_respondidas, respuestas_usadas"], unique = true)])
data class GameSave(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "preguntas_respondidas") val preguntas_respondidas: String,
    @ColumnInfo(name = "respuestas_usadas") val respuestas_usadas: String
)