package com.example.roomusingrxjava.database

import androidx.room.*
import com.example.roomusingrxjava.model.dataClass.User
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface AppDao {
    //Table for User
    @Insert
    fun insertUser(user: User):Completable

    @Update
    fun updateUser(user: User):Completable

    @Delete
    fun deleteUser(user: User):Completable

    @Query("DELETE FROM user_detail")
    fun deleteAllUser():Completable

    @Query("SELECT * FROM user_detail")
    fun readAllUser(): Flowable<List<User>>
}