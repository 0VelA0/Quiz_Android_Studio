package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.UserDao

@Database(entities = [User::class], version = 1)
abstract class bd : RoomDatabase() {
    abstract fun UserDao():UserDao
}