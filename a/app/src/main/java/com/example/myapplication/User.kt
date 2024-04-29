package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = [Index(value = ["Username","HasOnGoingGame"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "Username") val username: String,
    @ColumnInfo(name = "HasOnGoingGame") val hasongoinggame: Boolean=false,

)