package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Preguntas::class,GameSave::class, OptionsHints::class, OptionsDificultad::class,OptionsCategory::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun UserDao():UserDao
    abstract fun PreguntasDao():PreguntasDao
    abstract fun GameSaveDao():GameSaveDao
    abstract fun OptionsHintsDao():OptionsHintsDao
    abstract fun OptionsDificultadDao():OptionsDificultadDao
    abstract fun OptionsCategoryDao():OptionsCategoryDao
}