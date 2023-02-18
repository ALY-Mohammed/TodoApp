package com.example.todoapp.Fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.todoapp.R
import com.example.todoapp.TodoDatabase.MyDatabase
import com.example.todoapp.TodoDatabase.Todo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar
import java.util.Objects

class AddBottomSheetFragment :BottomSheetDialogFragment() {

    lateinit var titleEditText : TextInputLayout
    lateinit var descriptionEditText: TextInputLayout
    lateinit var addBottom : AppCompatButton
    lateinit var  selectedDateTV: TextView
    var selectedDate:Calendar=Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        listeners()

    }

    var onAddClick:OnAddClick?=null

    interface OnAddClick{
        fun onAddClicked()
    }


    fun listeners(){
        selectedDateTV.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),object :DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    selectedDate.set(year,month,dayOfMonth)
                    selectedDateTV.text="${selectedDate.get(Calendar.DAY_OF_MONTH)} / ${selectedDate.get(Calendar.MONTH)+1} / ${selectedDate.get(Calendar.YEAR)}"
                }

            },selectedDate.get(Calendar.YEAR),selectedDate.get(Calendar.MONTH),selectedDate.get(Calendar.DAY_OF_MONTH)
            )
            dialog.show()
        }

        addBottom.setOnClickListener {
            if(!Validat()) return@setOnClickListener
            selectedDate.clear(Calendar.MILLISECOND)
            selectedDate.clear(Calendar.MINUTE)
            selectedDate.clear(Calendar.HOUR)
            selectedDate.clear(Calendar.SECOND)
            val todo= Todo(
                title = titleEditText.editText!!.text.toString(),
                description = descriptionEditText.editText!!.text.toString(),
                isDone = false,
                date = selectedDate.time.time)
            MyDatabase.getinstance(requireContext()).getTodoDao().addTodo(todo)
            onAddClick?.onAddClicked()
            dismiss()
        }



    }



    fun initViews(view:View){
        titleEditText=view.findViewById(R.id.titleTextInputLayout)
        descriptionEditText= view.findViewById(R.id.descriptionTextInputLayout)
        addBottom=view.findViewById(R.id.AddButton)
        selectedDateTV=view.findViewById(R.id.Date_to_Select)
        selectedDateTV.text="${selectedDate.get(Calendar.DAY_OF_MONTH)}/${selectedDate.get(Calendar.MONTH)+1}/${selectedDate.get(Calendar.YEAR)}"

    }

    fun Validat():Boolean{
        var isvalid =true
        if (titleEditText.editText!!.text.isEmpty()){
            titleEditText.error="please Enter Task Title"
            isvalid=false
        }
        else{
            titleEditText.error=null
        }


        if (descriptionEditText.editText!!.text.isEmpty()){
            descriptionEditText.error="please Enter Task description"
            isvalid=false
        }
        else{
            descriptionEditText.error=null
        }

        return isvalid
    }

}