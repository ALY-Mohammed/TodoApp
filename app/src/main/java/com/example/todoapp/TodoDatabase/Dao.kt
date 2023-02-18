package com.example.todoapp.TodoDatabase

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*

@androidx.room.Dao
interface Dao {

    @Insert
    fun addTodo (todo:Todo)
    @Update
    fun updateTodo(todo:Todo)
    @Delete
    fun deleteTodo(todo: Todo)
    @Query("select * from Todo")
    fun getTodo():List<Todo>
    @Query("Select * from Todo Where date =:date")
    fun getTodoByDate(date:Long):List<Todo>

}