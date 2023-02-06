package com.example.todoapplicaton.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id:Int ,
    @ColumnInfo
    var title:String?=null,
    @ColumnInfo
    var description:String?=null,
    @ColumnInfo
    var date:Long?=null,
    @ColumnInfo
    var isDone:Boolean=false


):Serializable{
    //constructor(title: String, description: String, isDone: Boolean, date: Long) : this()

}