package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myapplication.Score

@Entity(tableName = "user", indices = [Index(value = ["firs_name", "last_name"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "lastestScore") val lastestScore: Score,
    @ColumnInfo(name = "bestScore") val bestScore: Score,
    @ColumnInfo(name = "passworld") val password: String

)