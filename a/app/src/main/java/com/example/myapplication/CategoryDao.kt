package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface CategoryDao{

    @Insert
    fun insertarcategoria(categoria: Category)

    @Insert
    fun insertcategorias(categoria1: Category, categoria2: Category)


    @Update
    fun updatecategoria(categoria: Array<Category>)

    @Update
    fun updateMorecategorias(vararg categoria: Category)

    @Delete
    fun deletecategoria(categoria: Category)

    @Delete
    fun deletecategorias(categoria:List<Category>)

}