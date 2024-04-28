package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myapplication.Score

@Entity(tableName = "user", indices = [Index(value = ["nombre"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "lastestScore") val lastestScore: Int,
    @ColumnInfo(name = "bestScore") val bestScore: Int,
    @ColumnInfo(name = "passworld") val password: String

)