package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface OptionsDao{

    @Insert
    fun insertoption(hint: Options)

    @Insert
    fun insertoptions(hint1:Options,hint2:Options)


    @Update
    fun updateoptions(hints: Array<Options>)

    @Update
    fun updateMoreoptions(vararg hint:Options)

    @Delete
    fun deleteoption(hint:Options)

    @Delete
    fun deleteoptions(hint:List<Options>)

}