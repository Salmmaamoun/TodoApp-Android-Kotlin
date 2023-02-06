package com.example.todoapplicaton.database.dao

import androidx.room.*
import com.example.todoapplicaton.database.model.Task

@Dao
interface TasksDao {
   @Insert
    fun insertTask(task:Task)
    @Delete
    fun deleteTask(task:Task)
    @Update
    fun updateTask(task:Task)
    @Query("select * from tasks")
    fun getAllTasks():List<Task>
 @Query("select * from tasks where date = :date ")
 fun getTodosByDate(date: Long):List<Task>


}