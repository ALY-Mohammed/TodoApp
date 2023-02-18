package com.example.todoapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.example.todoapp.TodoDatabase.MyDatabase
import com.example.todoapp.TodoDatabase.Todo
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class UpdateActivity : AppCompatActivity() {

    lateinit var titleTV :TextInputLayout
    lateinit var descriptionTV :TextInputLayout
    lateinit var saveChanges :Button
    lateinit var  selectedDateTV: TextView
    var selectedDate:Calendar=Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

       val idTOdo= intent.getStringExtra("id")
       val idtOdo=idTOdo?.toInt()

        initViews()
        listeners(idtOdo!!)

    }

    fun initViews(){
        titleTV=findViewById(R.id.titelTextInputLayout_U)
        descriptionTV=findViewById(R.id.descreptionTextInputLayout_U)
        saveChanges=findViewById(R.id.changeButton)
        selectedDateTV=findViewById(R.id.Date_to_Select_U)
        val title:String?  = intent.getStringExtra("T")
        val description:String?  = intent.getStringExtra("D")
        titleTV.editText!!.setText(title)
        descriptionTV.editText!!.setText(description)
   //     homeTodoApp.listfragment.refreshList()

    }


    fun listeners(idtodo:Int){
        selectedDateTV.setOnClickListener {
            val dialog = DatePickerDialog(this,object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    selectedDate.set(year,month,dayOfMonth)
                    selectedDateTV.text="${selectedDate.get(Calendar.DAY_OF_MONTH)} / ${selectedDate.get(
                        Calendar.MONTH)+1} / ${selectedDate.get(Calendar.YEAR)}"
                }

            },selectedDate.get(Calendar.YEAR),selectedDate.get(Calendar.MONTH),selectedDate.get(
                Calendar.DAY_OF_MONTH)
            )
            dialog.show()
        }

        saveChanges.setOnClickListener {
            if(!Validat()) return@setOnClickListener
            selectedDate.clear(Calendar.MILLISECOND)
            selectedDate.clear(Calendar.MINUTE)
            selectedDate.clear(Calendar.HOUR)
            selectedDate.clear(Calendar.SECOND)
            val todo= Todo(
                title = titleTV.editText!!.text.toString(),
                description = descriptionTV.editText!!.text.toString(),
                isDone = false,
                date = selectedDate.time.time,
                id=idtodo
            )
            MyDatabase.getinstance(this).getTodoDao().updateTodo(todo)
            val intent = Intent (this,HomeTodoApp::class.java)
            startActivity(intent)

        }

    }


    fun Validat():Boolean{
        var isvalid =true
        if (titleTV.editText!!.text.isEmpty()){
            titleTV.error="please Enter Task Title"
            isvalid=false
        }
        else{
            descriptionTV.error=null
        }


        if (descriptionTV.editText!!.text.isEmpty()){
            descriptionTV.error="please Enter Task description"
            isvalid=false
        }
        else{
            descriptionTV.error=null
        }

        return isvalid
    }

}