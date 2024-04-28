package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Preguntas::class,GameSave::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun UserDao():UserDao
    abstract fun PreguntasDao():PreguntasDao
    abstract fun GameSaveDao():GameSaveDao
}