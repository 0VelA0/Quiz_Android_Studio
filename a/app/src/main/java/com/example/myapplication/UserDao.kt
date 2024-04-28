package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao{

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserByID(id:Int): List<User>

    @Query("SELECT * FROM user WHERE id IN (:ids)")
    fun getUserByID(ids:List<Int>): List<User>

    @Insert
    fun insertUser(user: User)

    @Insert
    fun insertUsers(user1:User,user2:User)


    @Update
    fun updateUsers(users: Array<User>)

    @Update
    fun updateMoreUsers(vararg users:User)

    @Delete
    fun deleteUser(user:User)

    @Delete
    fun deleteUsers(users:List<User>)

}