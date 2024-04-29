package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "respuestafalsa", foreignKeys = [
    ForeignKey(
        entity = Preguntas::class,
        parentColumns = ["id"],
        childColumns = ["Preguntaid"],
        onDelete = ForeignKey.CASCADE
    )
])
data class RespuestaFalsa(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "Preguntaid") val preguntaid : Long,
    @ColumnInfo(name = "Respuesta") val respuesta : String
)

