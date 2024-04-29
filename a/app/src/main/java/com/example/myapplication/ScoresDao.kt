package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface ScoresDao{

    @Insert
    fun insertscore(score: Scores)

    @Insert
    fun insertscores(score1: Scores, score2: Scores)


    @Update
    fun updaterescore(score: Array<Scores>)

    @Update
    fun updateMorescores(vararg score: Scores)

    @Delete
    fun deletescore(score: Scores)

    @Delete
    fun deletescores(score:List<Scores>)

}