package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface OptionsCategoryDao{

    @Insert
    fun insertarcategoria(categoria: OptionsCategory)

    @Insert
    fun insertcategorias(categoria1: OptionsCategory,categoria2: OptionsCategory)


    @Update
    fun updatecategoria(categoria: Array<OptionsCategory>)

    @Update
    fun updateMorecategorias(vararg categoria: OptionsCategory)

    @Delete
    fun deletecategoria(categoria: OptionsCategory)

    @Delete
    fun deletecategorias(categoria:List<OptionsCategory>)

}