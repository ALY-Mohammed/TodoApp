package com.example.todoapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.example.todoapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zerobranch.layout.SwipeLayout


class DeleteFragment : BottomSheetDialogFragment() {

    lateinit var deleteButton:Button
    lateinit var cancelButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deleteButton=view.findViewById(R.id.delete)
        cancelButton=view.findViewById(R.id.Cancel)
        initListner()
    }

    fun initListner(){
        deleteButton.setOnClickListener {
            deleteTodo?.deleteTodo()
            dismiss()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }
    }

    var deleteTodo:DeleteTodo?=null

    interface DeleteTodo{
        fun deleteTodo()
    }


}