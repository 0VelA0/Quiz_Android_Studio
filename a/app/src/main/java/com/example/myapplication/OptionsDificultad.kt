package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "optionsdificultad", indices = [Index(value = ["Dificultad", "Interruptor"], unique = true)])
data class OptionsDificultad(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "Dificultad") val dificultad : String,
    @ColumnInfo(name = "Interruptor") val interruptor : Boolean
)

