package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface OptionsHintsDao{

    @Insert
    fun inserthint(hint: OptionsHints)

    @Insert
    fun inserthints(hint1:OptionsHints,hint2:OptionsHints)


    @Update
    fun updatehints(hints: Array<OptionsHints>)

    @Update
    fun updateMorehints(vararg hint:OptionsHints)

    @Delete
    fun deleteHint(hint:OptionsHints)

    @Delete
    fun deleteHints(hint:List<OptionsHints>)

}