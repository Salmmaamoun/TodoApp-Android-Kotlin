package com.example.todoapplicaton.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.todoapplicaton.database.dao.TasksDao
import com.example.todoapplicaton.database.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun getTaskDao(): TasksDao


    companion object{
        private  var myDataBase: MyDataBase?=null
        val DATABASENAME = "TasksDatabase"
        fun getDatabase(context: Context): MyDataBase {

            if (myDataBase == null) {
                myDataBase =
                    Room.databaseBuilder(context,MyDataBase::class.java,DATABASENAME).allowMainThreadQueries().build()
            }
            return myDataBase!!
        }
    }
}