package com.example.myapplication

import android.text.BoringLayout
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "options_category", indices = [Index(value = ["categoria","interruptor"], unique = true)])
data class OptionsCategory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "categoria") val categoria: String,
    @ColumnInfo(name = "interruptor") val interruptor: Boolean
)