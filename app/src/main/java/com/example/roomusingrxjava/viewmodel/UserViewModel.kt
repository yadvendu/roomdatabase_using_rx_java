package com.example.roomusingrxjava.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.roomusingrxjava.model.dataClass.User
import com.example.roomusingrxjava.model.repository.UserRepo
import io.reactivex.Completable
import io.reactivex.Flowable

class UserViewModel ():ViewModel() {
    lateinit var repo:UserRepo

    fun init(context: Context){
        repo = UserRepo(context)
    }

    fun insertUser(user:User):Completable?{
        return repo.insertUser(user)
    }

    fun readAllUser():Flowable<List<User>>?{
        return repo.getUser()
    }

    fun updateUser(user: User):Completable?{
        return repo.updateUser(user)
    }

    fun deleteUser(user: User):Completable?{
        return repo.deleteUser(user)
    }

    fun deleteAllUser():Completable?{
        return repo.deleteAllUser()
    }
}