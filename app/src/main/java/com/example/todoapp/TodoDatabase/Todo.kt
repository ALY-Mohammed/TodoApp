package com.example.todoapp.TodoDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id:Int=0,
    @ColumnInfo
    val title:String,
    @ColumnInfo
    val description:String,
    @ColumnInfo
    val date:Long,
    @ColumnInfo
    var isDone:Boolean=false
)
