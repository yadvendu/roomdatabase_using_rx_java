package com.example.roomusingrxjava.model.dataClass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_detail")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    @ColumnInfo(name = "user_name")
    var name:String? = null,
    @ColumnInfo(name = "user_age")
    var age:String? = null
)