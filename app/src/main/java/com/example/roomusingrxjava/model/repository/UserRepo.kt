package com.example.roomusingrxjava.model.repository

import android.content.Context
import com.example.roomusingrxjava.database.AppDao
import com.example.roomusingrxjava.database.AppDatabase
import com.example.roomusingrxjava.model.dataClass.User
import io.reactivex.Completable
import io.reactivex.Flowable

class UserRepo(context: Context){
    private var appDao: AppDao? = null

    init {
        val appDatabase = AppDatabase.getAppDataBaseInstance(context)
        appDao = appDatabase?.appDao()
    }

    fun getUser():Flowable<List<User>>?{
        return appDao?.readAllUser()
    }

    fun insertUser(user: User):Completable?{
        return appDao?.insertUser(user)
    }

    fun updateUser(user: User):Completable?{
        return appDao?.updateUser(user)
    }

    fun deleteUser(user: User):Completable?{
        return appDao?.deleteUser(user)
    }

    fun deleteAllUser():Completable?{
        return appDao?.deleteAllUser()
    }
}