package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(tableName = "preguntas", foreignKeys =[
    ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["categoriaid"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Preguntas(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "pregunta") val pregunta: String,
    @ColumnInfo(name = "respuesta") val respuesta: String,
    @ColumnInfo(name = "categoriaid") val categoriaid : Int,

)