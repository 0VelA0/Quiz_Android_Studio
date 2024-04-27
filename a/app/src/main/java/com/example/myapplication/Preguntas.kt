package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(tableName = "preguntas", indices = [Index(value = ["pregunta", "respuesta"], unique = true)])
data class Preguntas(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "pregunta") val pregunta: String,
    @ColumnInfo(name = "respuesta") val respuesta: String,
    @ColumnInfo(name = "categoria") val categoria : String
)