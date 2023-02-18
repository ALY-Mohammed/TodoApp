package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.example.todoapp.Fragments.AddBottomSheetFragment
import com.example.todoapp.Fragments.DeleteFragment
import com.example.todoapp.Fragments.SettingFragment
import com.example.todoapp.TodoDatabase.MyDatabase
import com.example.todoapp.TodoDatabase.Todo
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class HomeTodoApp : AppCompatActivity() {

    lateinit var buttonNavigationView:BottomNavigationView
    lateinit var fab:FloatingActionButton
     val deleteFragment = DeleteFragment()
    var selectedDatePackage: Calendar = Calendar.getInstance()
    val listfragment = com.example.todoapp.Fragments.ListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_todo_app)
        pushFragment(listfragment)
       // listfragment.refreshList()
        intiViews()
        listeners()


    }


    fun intiViews() {
        buttonNavigationView = findViewById(R.id.BottonNavigationView)
        buttonNavigationView.background=null
        fab=findViewById(R.id.Fab)
    }


    fun listeners(){
        buttonNavigationView.setOnItemSelectedListener {
            if(it.itemId == R.id.listicon){
                pushFragment(listfragment)
            }
            else{
                pushFragment(SettingFragment())
            }

            return@setOnItemSelectedListener true
        }


        //////////////// fab

        fab.setOnClickListener {
            val addBottomSheet = AddBottomSheetFragment()
            addBottomSheet.onAddClick=object :AddBottomSheetFragment.OnAddClick{
                override fun onAddClicked() {
                    listfragment.refreshList()
                }
            }
            addBottomSheet.show(supportFragmentManager, "")
        }


        ////////////////delete todo
        listfragment.callBackDeleteButton=object :com.example.todoapp.Fragments.ListFragment.CallBackDeleteButton{
            override fun deleet(position: Int, todo: Todo) {
                deleteFragment.deleteTodo = object : DeleteFragment.DeleteTodo{
                    override fun deleteTodo() {
                        MyDatabase.getinstance(this@HomeTodoApp).getTodoDao().deleteTodo(todo)
                        listfragment.refreshList()
                    }
                }
                deleteFragment.show(supportFragmentManager,"delete F")

            }

        }

    }

    fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContaner,fragment)
            .commit()
    }



}