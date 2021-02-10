package com.example.roomusingrxjava.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomusingrxjava.model.dataClass.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun appDao():AppDao

    companion object{
        private const val DATABASE_NAME = "app_db"
        var instance:AppDatabase? = null

        fun getAppDataBaseInstance(context: Context):AppDatabase?{
            if (instance == null){
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}