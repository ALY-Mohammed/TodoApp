package com.example.todoapp.TodoDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Todo::class], version = 1)
abstract class MyDatabase:RoomDatabase() {

   abstract fun getTodoDao():Dao

  companion object{

      private  var db :MyDatabase?=null

      //دا اسمه هبلعشان كل ما اجي استخدم ل fun اعمل داتا بيز جديده مش نافع كدا الحد اعما متغير او تشيك عليه لو ب null اكريت الداتا بيز

      fun getinstance(context:Context):MyDatabase{
          if (db ==null) {
              db = Room.databaseBuilder(
                  context,
                  MyDatabase::class.java,
                  "MyDatabase"
              ).allowMainThreadQueries()
                  .fallbackToDestructiveMigration()
                  .build()
          }

          return db!!
      }
  }

}