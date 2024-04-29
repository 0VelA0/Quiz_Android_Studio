package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "scores", foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userid"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Scores(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "userid") val userid : Long,
    @ColumnInfo(name = "Score") val score : Int
)

