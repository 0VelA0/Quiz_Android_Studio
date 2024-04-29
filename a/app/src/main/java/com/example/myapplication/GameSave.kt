package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "game_save", foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userid"],
        onDelete = ForeignKey.CASCADE
    )
])
data class GameSave(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "userid") val userid: Long,
    @ColumnInfo(name = "seedpreguntas") val seedpreguntas: String,
    @ColumnInfo(name = "RespondidasPreguntas") val respondidaspreguntas: String,
    @ColumnInfo(name = "RespondidasCorrectasPreguntas") val respondidascorrectaspreguntas: String,
    @ColumnInfo(name = "HintContadorPreguntas") val hintcontadorpreguntas: String,
    @ColumnInfo(name = "HintsActuales") val hintsactuales:Int
)